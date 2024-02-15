package com.jedco.jedcoengineeringspring.services;

import com.jedco.jedcoengineeringspring.rest.request.TxReadingRequest;
import com.jedco.jedcoengineeringspring.rest.response.CreateBoxNumberResponse;
import com.jedco.jedcoengineeringspring.rest.response.PoleResponse;
import com.jedco.jedcoengineeringspring.rest.response.TransformerResponse;
import com.jedco.jedcoengineeringspring.rest.response.TxResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TxDataServiceImpl implements TxDataService {
    @Override
    public List<String> listFeeder() {
        return null;
    }

    @Override
    public List<TxResponse> listTxByFeeder(String feeder) {
        return null;
    }

    @Override
    public List<PoleResponse> listPoleByFeederTx(String feeder, String tx) {
        return null;
    }

    @Override
    public List<TransformerResponse> listTransformerByFeeder(String feeder) {
        return null;
    }

    @Override
    public List<TransformerResponse> listTransformer() {
        return null;
    }

    @Override
    public List<TransformerResponse> listTransformerByFeederAndTx(String feeder, String txCode) {
        return null;
    }

    @Override
    public List<TransformerResponse> listTransformerByDate(String date) {
        return null;
    }

    @Override
    public void addTxReadingWithLineReadings(Long txReadingId, TxReadingRequest txReadingDTO, String username) {

    }

    @Override
    public CreateBoxNumberResponse createBoxNumber(Long poleId, String username) {
        return null;
    }
}
