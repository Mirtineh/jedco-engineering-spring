package com.jedco.jedcoengineeringspring.services;

import com.jedco.jedcoengineeringspring.Util.DateConverter;
import com.jedco.jedcoengineeringspring.Util.Day;
import com.jedco.jedcoengineeringspring.mappers.PoleDataMapper;
import com.jedco.jedcoengineeringspring.models.*;
import com.jedco.jedcoengineeringspring.repositories.*;
import com.jedco.jedcoengineeringspring.rest.request.LvDataRegisterRequest;
import com.jedco.jedcoengineeringspring.rest.request.LvMeterDataRequest;
import com.jedco.jedcoengineeringspring.rest.response.LongLatResponse;
import com.jedco.jedcoengineeringspring.rest.response.LvDataResponse;
import com.jedco.jedcoengineeringspring.rest.response.LvMeterResponse;
import com.jedco.jedcoengineeringspring.rest.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LvDataServiceImpl implements LvDataService {
    private final MeterRepository meterRepository;
    private final MeterDataRepository meterDataRepository;
    private final UserRepository userRepository;
    private final PoleDataRepository poleDataRepository;
    private final StatusRepository statusRepository;
    private final TxInfoRepository txInfoRepository;
    private final BoxNumberRepository boxNumberRepository;

    private final PoleDataMapper poleDataMapper;
    private final UTMtoLangLatService utMtoLangLatService;
    private final DateConverter dateConverter;
    @Override
    public ResponseDto checkMeter(String meterNo) {
        Optional<Meter> optionalMeter= meterRepository.findByMeterNo(meterNo);
        if(optionalMeter.isEmpty()){
            return new ResponseDto(false, "Meter Not found!");
        }
        return new ResponseDto(true, "Meter found!");
    }

    @Override
    public List<LvDataResponse> getDataByUser(String date, String username) {
        Day day= dateConverter.convertToStartAndEndDate(date);
        User user = userRepository.findByUsername(username).get();
        List<PoleData>  poleDataList;
        if(username.equals("abe")){
            poleDataList= poleDataRepository.findAllBySubmittedOnBetween(day.startTime(),day.endTime());
        }else{
            poleDataList= poleDataRepository.findAllByRegisteredByAndSubmittedOnBetween(user, day.startTime(), day.endTime());
        }
        return getLvDataResponses(poleDataList);

    }

    @Override
    public List<LvDataResponse> getDataByFeederAndTx(String feeder, String txCode) {
        List<PoleData> poleDataList= poleDataRepository.findAllByFeederAndTxNo(feeder,txCode);
        return getLvDataResponses(poleDataList);
    }

    private List<LvDataResponse> getLvDataResponses(List<PoleData> poleDataList) {
        Long activeStatus= 1L;
        return poleDataList.stream().map(poleData -> {
            List<MeterData> meterDataList = meterDataRepository.findByPoleDataAndStatusId(poleData, activeStatus);
            return poleDataMapper.toLvDataResponse(poleData, meterDataList);
        }).toList();
    }

    @Override
    public List<LvDataResponse> getDataByFeederTxPole(String feeder, String txCode, String poleNo) {
        List<PoleData> poleDataList= poleDataRepository.findByFeederAndTxNoAndPoleNo(feeder,txCode,poleNo);
        return getLvDataResponses(poleDataList);
    }

    @Override
    public List<LvDataResponse> getDataByPoleNo(String poleNo) {
        List<PoleData> poleDataList= poleDataRepository.findByPoleNo(poleNo);
        return getLvDataResponses(poleDataList);
    }

    @Override
    public ResponseDto insertLvData(LvDataRegisterRequest lvDataRegisterDto, String username) {
        try {
            Optional<PoleData> optionalPoleData = poleDataRepository.findOneByPoleNoAndTxNo(lvDataRegisterDto.poleNo(), lvDataRegisterDto.txNo());
            if (optionalPoleData.isPresent()) {
                return new ResponseDto(false, "Pole No. Already registered with the same Tx Code.");
            }

            Long activeStatus= 1L;
            for (LvMeterDataRequest meter : lvDataRegisterDto.meterDataDtoList()) {
                MeterData meterData = meterDataRepository.findOneByMeterNoAndStatusId(meter.meterNo(),activeStatus);
                if(meterData!=null){
                    return new ResponseDto(false, "Meter No. "+meter.meterNo()+" Already registered in the system");
                }
            }
            LongLatResponse longLatResponse= utMtoLangLatService.convertUTMToLangLat(lvDataRegisterDto.northing(), lvDataRegisterDto.easting());
            Status status = statusRepository.findById(activeStatus).get();
            User user = userRepository.findByUsername(username).get();

            PoleData poleData = new PoleData();
            poleData.setFeeder(lvDataRegisterDto.feeder());
            poleData.setAssemblyType(lvDataRegisterDto.assemblyType());
            poleData.setBranchCode(lvDataRegisterDto.branchCode());
            poleData.setConductorType(lvDataRegisterDto.conductorType());
            poleData.setPoleType(lvDataRegisterDto.poleType());
            poleData.setPoleFeature(lvDataRegisterDto.poleFeature());
            poleData.setPoleNo(lvDataRegisterDto.poleNo());
            poleData.setTxNo(lvDataRegisterDto.txNo());
            poleData.setRegisteredBy(user);
            poleData.setStatus(status);
            poleData.setLatitude(longLatResponse.latitude());
            poleData.setLongitude(longLatResponse.longitude());
            poleData.setLocationAccuracy(lvDataRegisterDto.locationAccuracy());
            poleData.setRemark(lvDataRegisterDto.remark());
            poleData.setSubmittedOn(new Date());
            poleData.setNorthing(lvDataRegisterDto.northing());
            poleData.setEasting(lvDataRegisterDto.easting());
            poleData.setPole_anomaly(lvDataRegisterDto.pole_anomaly());
            poleData.setPoleRegType(lvDataRegisterDto.poleRegisterationType());
            poleData.setUpdatedOn(new Date());
            poleData.setUpdatedBy(user.getFirstName()+" "+user.getLastName());
            poleDataRepository.save(poleData);
            BoxNumber boxNumber=new BoxNumber();
            Date boxNumberDate= new Date();
            boxNumber.setCreatedBy(user);
            boxNumber.setCreatedOn(boxNumberDate);
            boxNumber.setUpdatedOn(boxNumberDate);
            boxNumber.setPoleData(poleData);
            createBoxNumber(poleData,boxNumber);

            for (LvMeterDataRequest meter : lvDataRegisterDto.meterDataDtoList()) {
                MeterData meterData = new MeterData();

                meterData.setComCableLength(meter.comCableLength());
                meterData.setConnectedPhase(meter.connectedPhase());
                meterData.setCustomerName(meter.customerName());
                meterData.setMeterNo(meter.meterNo());
                meterData.setCustomerType(meter.customerType());
                meterData.setEstimatedLoad(meter.estimatedLoad());
                meterData.setMeterType(meter.meterType());
                meterData.setServiceCableLength(meter.serviceCableLength());
                meterData.setServiceCableType(meter.serviceCableType());
                meterData.setPoleData(poleData);
                meterData.setStatus(status);
                meterData.setMeterAnomaly(meter.meterAnomaly());
                meterData.setBoxAssemblyType(meter.assemblyType());
                meterData.setRegisteredBy(user.getId());
                meterData.setRegisteredOn(new Date());
                meterData.setBoxAssemblyType(meter.assemblyType());
                meterData.setMeterRegType("COMMISSIONING");
                meterData.setBoxNumber(boxNumber);
                meterDataRepository.save(meterData);

            }

            return new ResponseDto(true, "Lv Network data Registered Successfully");
        } catch (Exception ex) {
            return new ResponseDto(false, "Lv Network data Registration Failed!");

        }
    }
    @Transactional
    protected Long createBoxNumber(PoleData poleData,BoxNumber boxNumber){
        Optional<TxInfo> optionalTransformer=txInfoRepository.findByTrafoCode(poleData.getTxNo());
        if(optionalTransformer.isEmpty()){
            return 0L;
        }
        var transformer= optionalTransformer.get();
        Long newSequence = transformer.getBoxSequence() + 1;
        transformer.setBoxSequence(newSequence);
        txInfoRepository.save(transformer);
        boxNumber.setBoxNumber(newSequence);
        boxNumberRepository.save(boxNumber);
        return newSequence;
    }

    @Override
    public ResponseDto updateLvData(LvDataResponse updateDto, String username) {
        try {
            Optional<PoleData> optionalPoleData = poleDataRepository.findById(updateDto.id());
            Optional<PoleData> optionalCheckData = poleDataRepository.findOneByPoleNoAndTxNo(updateDto.poleNo(), updateDto.txNo());

            if(optionalPoleData.isEmpty()){
                return new ResponseDto(false, "Pole Not Found");
            }
            var poleData= optionalPoleData.get();
            if (optionalCheckData.isPresent() && !optionalCheckData.get().getId().equals(poleData.getId())) {
                return new ResponseDto(false, "Pole No. Already registered with the same Tx Code.");
            }

            List<String> updateMeters = new ArrayList<>();
            Long activeStatus= 1L;
            Long deletedStatus= 3L;

            for (LvMeterResponse meter : updateDto.meterDataDtoList()) {
                if(meter.id()==null){
                    MeterData meterData = meterDataRepository.findOneByMeterNoAndStatusId(meter.meterNo(),activeStatus);
                    if(meterData!=null){
                        return new ResponseDto(false, "Meter No. "+meter.meterNo()+" Already registered in the system");
                    }
                }

                updateMeters.add(meter.meterNo());
            }


            Status status = statusRepository.findById(activeStatus).get();
            User user = userRepository.findByUsername(username).get();


            LongLatResponse longLatResponse= this.utMtoLangLatService.convertUTMToLangLat(updateDto.northing(), updateDto.easting());
            poleData.setFeeder(updateDto.feeder());
            poleData.setAssemblyType(updateDto.assemblyType());
            poleData.setBranchCode(updateDto.branchCode());
            poleData.setConductorType(updateDto.conductorType());
            poleData.setPoleType(updateDto.poleType());
            poleData.setPoleFeature(updateDto.poleFeature());
            poleData.setPoleNo(updateDto.poleNo());
            poleData.setTxNo(updateDto.txNo());
            poleData.setStatus(status);
            poleData.setLatitude(longLatResponse.latitude());
            poleData.setLongitude(longLatResponse.longitude());
            poleData.setLocationAccuracy(updateDto.locationAccuracy());
            poleData.setRemark(updateDto.remark());
            poleData.setNorthing(updateDto.northing());
            poleData.setEasting(updateDto.easting());
            poleData.setPole_anomaly(updateDto.pole_anomaly());
            poleData.setPoleRegType(updateDto.poleRegistrationType());
            poleData.setUpdatedOn(new Date());
            poleData.setUpdatedBy(user.getFirstName()+" "+user.getLastName());
            poleDataRepository.save(poleData);

            for (MeterData meter : poleData.getMeterDataSet()) {
                if(!updateMeters.contains(meter.getMeterNo())){
                    meter.setStatus(statusRepository.findById(deletedStatus).get());
                    meterDataRepository.save(meter);
                }
            }

            for (LvMeterResponse meter : updateDto.meterDataDtoList()) {
                MeterData meterData;
                if(meter.id()!=null){
                    //TODO check for side effects
                    meterData = meterDataRepository.findById(meter.id()).get();
                    poleUpdateData(poleData, status, meter, meterData);
                    meterDataRepository.save(meterData);
                }else{
                    meterData  = new MeterData();
                    poleUpdateData(poleData, status, meter, meterData);
                    meterData.setRegisteredBy(user.getId());
                    meterData.setMeterRegType("COMMISSIONING");
                    meterData.setRegisteredOn(new Date());
                    meterDataRepository.save(meterData);
                }


            }



//            this.updateHistoryDao.create(updateHistory);

            return new ResponseDto(true, "Lv Network data Registered Successfully");
        } catch (Exception ex) {
            return new ResponseDto(false, "Lv Network data Registration Failed!");

        }
    }
    private void poleUpdateData(PoleData poleData, Status status, LvMeterResponse meter, MeterData meterData) {
        meterData.setComCableLength(meter.comCableLength());
        meterData.setConnectedPhase(meter.connectedPhase());
        meterData.setCustomerName(meter.customerName());
        meterData.setMeterNo(meter.meterNo());
        meterData.setCustomerType(meter.customerType());
        meterData.setEstimatedLoad(meter.estimatedLoad());
        meterData.setMeterType(meter.meterType());
        meterData.setServiceCableLength(meter.serviceCableLength());
        meterData.setServiceCableType(meter.serviceCableType());
        meterData.setPoleData(poleData);
        meterData.setStatus(status);
        meterData.setMeterAnomaly(meter.meterAnomaly());
        meterData.setBoxNumber(boxNumberRepository.findById(meter.boxNoId()).get());
        meterData.setBoxAssemblyType(meter.assemblyType());
    }
}
