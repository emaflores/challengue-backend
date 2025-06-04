package com.emaflores.challenge.service;

import com.emaflores.challenge.dto.CalculationRequest;
import com.emaflores.challenge.dto.CalculationResponse;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class CalculationServiceImpl implements CalculationService{
    private final PercentageService percentageService;
    private final CacheManager cacheManager;

    public CalculationServiceImpl(PercentageService percentageService, CacheManager cacheManager) {
        this.percentageService = percentageService;
        this.cacheManager = cacheManager;
    }

    @Override
    public CalculationResponse calculateWithPercentage(CalculationRequest request) {
        double sum = request.getNum1() + request.getNum2();
        double percentage;
        try {
            percentage = percentageService.getCurrentPercentage();
        } catch (Exception e) {
            var cached = cacheManager.getCache("percentage");
            var cachedValue = cached != null ? cached.get("percentage", Double.class) : null;

            if (cachedValue != null){
                percentage = cachedValue;
            } else {
                throw new IllegalStateException("No se pudo obtener el porcentaje ni hay valor en la cache");
            }

        }

        double result = sum + (sum * percentage / 100.0);
        return new CalculationResponse(sum, percentage, result);
    }
}
