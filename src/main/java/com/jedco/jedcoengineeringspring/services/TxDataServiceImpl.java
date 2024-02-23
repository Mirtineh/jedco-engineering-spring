package com.jedco.jedcoengineeringspring.services;

import com.jedco.jedcoengineeringspring.Util.DateConverter;
import com.jedco.jedcoengineeringspring.Util.Day;
import com.jedco.jedcoengineeringspring.mappers.TxMapper;
import com.jedco.jedcoengineeringspring.models.*;
import com.jedco.jedcoengineeringspring.repositories.*;
import com.jedco.jedcoengineeringspring.rest.request.TxLineReadingRequest;
import com.jedco.jedcoengineeringspring.rest.request.TxReadingRequest;
import com.jedco.jedcoengineeringspring.rest.response.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class TxDataServiceImpl implements TxDataService {
    private final TxInfoRepository txInfoRepository;
    private final TxReadingRepository txReadingRepository;
    private final PoleDataRepository poleDataRepository;
    private final UserRepository userRepository;
    private final BoxNumberRepository boxNumberRepository;
    private final FeederRepository feederRepository;
    private final TxLineReadingRepository txLineReadingRepository;

    private final TxMapper txMapper;
    private final DateConverter dateConverter;

    @Override
    public List<String> listFeeder() {
//        return txInfoRepository.findDistinctByFeederCode();
        return feederRepository.findAll().stream().map(Feeder::getName).toList();
    }

    @Override
    public List<TxResponse> listTxByFeeder(String feeder) {
        List<TxInfo> txInfoList = txInfoRepository.findAllByFeederCode(feeder);
        return txInfoList.stream().map(txMapper::toTxResponse).toList();
    }

    @Override
    public List<PoleResponse> listPoleByFeederTx(String feeder, String tx) {
//        List<PoleData> poleDataList = poleDataRepository.findAllByFeederAndTxNo(feeder, tx);
        List<PoleData> poleDataList = poleDataRepository.findAllByTransformerTrafoCodeAndTransformerFeederCode(tx, feeder);
        return poleDataList.stream().map(txMapper::toPoleResponse).toList();
    }

    @Override
    public List<TransformerResponse> listTransformerByFeeder(String feeder) {
        List<TxInfo> txInfoList = txInfoRepository.findAllByFeederCode(feeder);
        return txInfoList.stream().map(txMapper::toTransformerResponse).toList();
    }

    @Override
    public List<TransformerResponse> listTransformer() {
        List<TxInfo> txInfoList = txInfoRepository.findAll();
        return txInfoList.stream().map(txMapper::toTransformerResponse).toList();
    }

    @Override
    public List<TransformerResponse> listTransformerByFeederAndTx(String feeder, String txCode) {
        List<TxInfo> txInfoList = txInfoRepository.findAllByFeederCodeAndTrafoCode(feeder, txCode);
        return txInfoList.stream().map(txMapper::toTransformerResponse).toList();
    }

    @Override
    public List<TransformerResponse> listTransformerByDate(String date) {
        Day day = dateConverter.convertToStartAndEndDate(date);
        List<TxInfo> txInfoList = txInfoRepository.findAllByRegisteredOnBetween(day.startTime(), day.endTime());
        return txInfoList.stream().map(txMapper::toTransformerResponse).toList();
    }

    @Override
    public void addTxReadingWithLineReadings(Long txReadingId, TxReadingRequest txReadingDTO, String username) {
        User user = userRepository.findByUsername(username).get();
        TxInfo transformer = txInfoRepository.findById(txReadingId).get();
        // Convert DTO to entity
        TxReading txReading = new TxReading();

        // Convert TxLineReadingDTO list to entities and set the relationship
        Set<TxLineReading> txLineReadings = new HashSet<>();
        for (TxLineReadingRequest txLineReadingDTO : txReadingDTO.lineReadings()) {
            TxLineReading txLineReading = new TxLineReading();
            txLineReading.setLine(txLineReadingDTO.line());
            txLineReading.setCurrent(txLineReadingDTO.current());
            txLineReading.setVoltage(txLineReadingDTO.voltage());
            txLineReading.setPower((txLineReadingDTO.current() * txLineReadingDTO.voltage() * 0.95) / 1000);
            txLineReading.setTxReading(txReading);
            // Set other fields as needed
            txLineReadings.add(txLineReading);
        }

        txReading.setLineReadings(txLineReadings);
        txReading.setBranch(txReadingDTO.branch());
        txReading.setNeutralCurrent(txReadingDTO.neutralCurrent());
        txReading.setRemark(txReadingDTO.remark());
        txReading.setCreatedOn(new Date());
        txReading.setTransformer(transformer);
        txReading.setCreatedBy(user);

        // Save the entities
        txReadingRepository.save(txReading);
    }

    @Override
    public CreateBoxNumberResponse createBoxNumber(Long poleId, String username) {
        User user = userRepository.findByUsername(username).get();
        PoleData poleData = poleDataRepository.findById(poleId).get();
        Date date = new Date();
        BoxNumber boxNumber = new BoxNumber();
        boxNumber.setPoleData(poleData);
        boxNumber.setCreatedBy(user);
        boxNumber.setCreatedOn(date);
        boxNumber.setUpdatedOn(date);
        Long number = createBoxNumber(poleData, boxNumber);
        if (number >= 0 && number <= 9) {
            return new CreateBoxNumberResponse("B0" + number);
        }
        return new CreateBoxNumberResponse("B" + number);
    }

    @Override
    public List<TxReadingResponse> getTxReadingByDate(String date,Long txId, String username) {
        User user = userRepository.findByUsername(username).get();
        Day day= dateConverter.convertToStartAndEndDate(date);
        List<TxReading> txReadingList = txReadingRepository.findAllByCreatedByAndTransformerIdAndCreatedOnBetween(user,txId,day.startTime(),day.endTime());
        return txReadingList.stream().map(txMapper::toTxReadingResponse).toList();
    }

    @Override
    public ResponseDto updateTxReading(TxReadingResponse txReadingUpdateRequest, String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isEmpty()){
            return new ResponseDto(false,"User not Found!");
        }
        var user= optionalUser.get();
        Optional<TxReading> optionalTxReading= txReadingRepository.findById(txReadingUpdateRequest.id());
        if(optionalTxReading.isEmpty()){
            return new ResponseDto(false,"Tx Reading not Found!");
        }
        var txReading = optionalTxReading.get();
        for(TxLineReadingResponse lineReadingResponse: txReadingUpdateRequest.lineReadings()){
            Optional<TxLineReading> optionalTxLineReading= txLineReadingRepository.findById(lineReadingResponse.id());
            if(optionalTxLineReading.isEmpty()){
                return new ResponseDto(false,"Line Reading not found");
            }
            var txLineReading= optionalTxLineReading.get();
            if(!txLineReading.getTxReading().equals(txReading)){
                return new ResponseDto(false,"Line Reading does not belong to Tx Reading!");
            }
            txLineReading.setLine(lineReadingResponse.line());
            txLineReading.setCurrent(lineReadingResponse.current());
            txLineReading.setVoltage(lineReadingResponse.voltage());
            txLineReading.setPower((lineReadingResponse.current() * lineReadingResponse.voltage() * 0.95) / 1000);
            txLineReadingRepository.save(txLineReading);
        }
        txReading.setBranch(txReadingUpdateRequest.branch());
        txReading.setNeutralCurrent(txReadingUpdateRequest.neutralCurrent());
        txReading.setRemark(txReadingUpdateRequest.remark());
        txReading.setUpdatedOn(new Date());
        txReading.setUpdatedBy(user);
        txReadingRepository.save(txReading);
        return new ResponseDto(true,"Reading Updated Successfully!");
    }

    //TODO Check this method carefully
    private Long createBoxNumber(PoleData poleData, BoxNumber boxNumber) {
        var transformer = poleData.getTransformer();
        Long newSequence = transformer.getBoxSequence() + 1;
        transformer.setBoxSequence(newSequence);
        txInfoRepository.save(transformer);
        boxNumber.setBoxNumber(newSequence);
        boxNumberRepository.save(boxNumber);
        return newSequence;
    }
}
