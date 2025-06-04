package com.emaflores.challenge.service;

import com.emaflores.challenge.dto.CalculationRequest;
import com.emaflores.challenge.dto.CalculationResponse;

public interface CalculationService {
    CalculationResponse calculateWithPercentage(CalculationRequest request);
}
