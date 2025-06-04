package com.emaflores.challenge.controller;

import com.emaflores.challenge.dto.CalculationRequest;
import com.emaflores.challenge.dto.CalculationResponse;
import com.emaflores.challenge.service.CalculationService;
import com.emaflores.challenge.service.CallLogService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CalculationController {
    private final CalculationService calculationService;
    private final CallLogService callLogService;

    public CalculationController(CalculationService calculationService, CallLogService callLogService){
        this.calculationService = calculationService;
        this.callLogService = callLogService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<CalculationResponse> calculate(@Valid @RequestBody CalculationRequest request) {
        try {
            CalculationResponse response = calculationService.calculateWithPercentage(request);
            callLogService.logCall(
                    "/api/calculate",
                    "num1=" + request.getNum1() + ", num2=" + request.getNum2(),
                    "result=" + response.getFinalResult(),
                    false
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            callLogService.logCall(
                    "/api/calculate",
                    "num1=" + request.getNum1() + ", num2=" + request.getNum2(),
                    "error=" + e.getMessage(),
                    true
            );
            throw e;
        }
    }
}
