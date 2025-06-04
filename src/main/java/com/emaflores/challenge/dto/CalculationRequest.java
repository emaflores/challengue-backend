package com.emaflores.challenge.dto;

import jakarta.validation.constraints.NotNull;

public class CalculationRequest {
    @NotNull(message = "num1 es obligatorio")
    private Double num1;
    @NotNull(message = "num2 es obligatorio")
    private Double num2;
    public CalculationRequest(Double num1, Double num2) {
        this.num1 = num1;
        this.num2 = num2;
    }
    public double getNum1() {
        return num1;
    }
    public void setNum1(Double num1) {
        this.num1 = num1;
    }
    public double getNum2() {
        return num2;
    }
    public void setNum2(Double num2) {
        this.num2 = num2;
    }
}

