package com.jedco.jedcoengineeringspring.controllers;

import com.jedco.jedcoengineeringspring.rest.request.TxReadingRequest;
import com.jedco.jedcoengineeringspring.rest.response.*;
import com.jedco.jedcoengineeringspring.services.TxDataService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/txData")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "TxData")
public class TxDataController {
    private final TxDataService txDataService;

    @GetMapping("/feederList")
    public List<String> listFeeder() {
        return this.txDataService.listFeeder();
    }

    @GetMapping("/txList")
    public List<TxResponse> listTx(@RequestParam("feeder") String feeder) {
        return this.txDataService.listTxByFeeder(feeder);
    }

    @GetMapping("/poleList")
    public List<PoleResponse> listPole(@RequestParam("feeder") String feeder, @RequestParam("tx") String tx) {
        return this.txDataService.listPoleByFeederTx(feeder, tx);
    }

    @GetMapping("/txListByFeeder")
    public List<TransformerResponse> listTransformer(@RequestParam("feeder") String feeder) {
        return this.txDataService.listTransformerByFeeder(feeder);
    }

    @GetMapping("/txListByFeederAndTxCode")
    public List<TransformerResponse> listTransformerByFeederAndTxCode(@RequestParam("feeder") String feeder, @RequestParam("tx") String txCode) {
        return this.txDataService.listTransformerByFeederAndTx(feeder, txCode);
    }

    @GetMapping("/listAllTx")
    public List<TransformerResponse> listTransformer() {
        return this.txDataService.listTransformer();
    }

    @GetMapping("/txListByDate")
    public List<TransformerResponse> listTransformerByDate(@RequestParam("date") String date) {
        return this.txDataService.listTransformerByDate(date);
    }

    @PostMapping("/{txReadingId}/txReading")
    @PreAuthorize("hasAnyAuthority('REGISTER_TRANSFORMER_LOADING')")
    public ResponseEntity<String> addTxReadingWithLineReadings(@PathVariable("txReadingId") Long txReadingId, @RequestBody TxReadingRequest txReadingDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        this.txDataService.addTxReadingWithLineReadings(txReadingId, txReadingDTO, userDetails.getUsername());
        return ResponseEntity.ok("{\"message\": \"TxReading with LineReadings added successfully\"}");
    }
    @GetMapping("/txReadingByDate")
    public List<TxReadingResponse> getTxReading(@RequestParam("date") String date){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return txDataService.getTxReadingByDate(date,userDetails.getUsername());
    }
    @PutMapping("/updateTxReading")
    public ResponseDto updateTxReading(@RequestBody TxReadingResponse txReadingUpdateRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return txDataService.updateTxReading(txReadingUpdateRequest,userDetails.getUsername());
    }

    @PostMapping("/createBoxNumber/{poleId}")
    @PreAuthorize("hasAnyAuthority('GENERATE_BOX_NUMBER')")
    public CreateBoxNumberResponse createBoxNumber(@PathVariable("poleId") Long poleId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return this.txDataService.createBoxNumber(poleId, userDetails.getUsername());
    }
}
