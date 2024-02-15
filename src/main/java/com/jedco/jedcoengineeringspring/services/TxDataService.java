package com.jedco.jedcoengineeringspring.services;

import com.jedco.jedcoengineeringspring.rest.request.TxReadingRequest;
import com.jedco.jedcoengineeringspring.rest.response.CreateBoxNumberResponse;
import com.jedco.jedcoengineeringspring.rest.response.PoleResponse;
import com.jedco.jedcoengineeringspring.rest.response.TransformerResponse;
import com.jedco.jedcoengineeringspring.rest.response.TxResponse;

import java.util.List;

public interface TxDataService {
    List<String> listFeeder();

    List<TxResponse> listTxByFeeder(String feeder);

    List<PoleResponse> listPoleByFeederTx(String feeder, String tx);

    List<TransformerResponse> listTransformerByFeeder(String feeder);

    List<TransformerResponse> listTransformer();

    List<TransformerResponse> listTransformerByFeederAndTx(String feeder, String txCode);

    List<TransformerResponse> listTransformerByDate(String date);

    void addTxReadingWithLineReadings(Long txReadingId, TxReadingRequest txReadingDTO, String username);

    CreateBoxNumberResponse createBoxNumber(Long poleId, String username);


}
