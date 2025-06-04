package com.emaflores.challenge.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PercentageServiceImpl implements PercentageService {

    private final Random random = new Random();
    private boolean falla = true;

    @Override
    @Cacheable("percentage")
    public double getCurrentPercentage() {
        //fallos
        falla = !falla;
        if (falla){
            throw new RuntimeException("Fallo en el servicio externo");
        }

        return 10.0;
    }
}
