package com.emaflores.challenge.dto;

public class CalculationResponse {
    private final double originalSum;
    private final double appliedPercentage;
    private final double finalResult;
    public CalculationResponse(double originalSum, double appliedPercentage, double finalResult) {
        this.originalSum = originalSum;
        this.appliedPercentage = appliedPercentage;
        this.finalResult = finalResult;
    }
    public double getOriginalSum() {
        return originalSum;
    }
    public double getAppliedPercentage() {
        return appliedPercentage;
    }
    public double getFinalResult() {
        return finalResult;
    }
}


