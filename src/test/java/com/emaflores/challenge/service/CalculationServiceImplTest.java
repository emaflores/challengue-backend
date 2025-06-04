package com.emaflores.challenge.service;

import com.emaflores.challenge.dto.CalculationRequest;
import com.emaflores.challenge.dto.CalculationResponse;
import org.junit.jupiter.api.Test;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CalculationServiceImplTest {

    @Test
    void testCalculationWithExternalPercentage() {

        PercentageService percentageService = mock(PercentageService.class);
        CacheManager cacheManager = mock(CacheManager.class);

        when(percentageService.getCurrentPercentage()).thenReturn(10.0);

        CalculationServiceImpl service = new CalculationServiceImpl(percentageService, cacheManager);

        CalculationRequest request = new CalculationRequest(100.0, 50.0);
        CalculationResponse response = service.calculateWithPercentage(request);

        assertEquals(150.0, response.getOriginalSum());
        assertEquals(10.0, response.getAppliedPercentage());
        assertEquals(165.0, response.getFinalResult());
    }

    @Test
    void testFallbackToCacheOnFailure() {
        PercentageService percentageService = mock(PercentageService.class);
        CacheManager cacheManager = mock(CacheManager.class);
        Cache cache = mock(Cache.class);

        when(percentageService.getCurrentPercentage()).thenThrow(new RuntimeException("Fallo"));

        when(cacheManager.getCache("percentage")).thenReturn(cache);
        when(cache.get("percentage", Double.class)).thenReturn(15.0);

        CalculationServiceImpl service = new CalculationServiceImpl(percentageService, cacheManager);

        CalculationRequest request = new CalculationRequest(100.0, 100.0);
        CalculationResponse response = service.calculateWithPercentage(request);

        assertEquals(200.0, response.getOriginalSum());
        assertEquals(15.0, response.getAppliedPercentage());
        assertEquals(230.0, response.getFinalResult());
    }

    @Test
    void testNoCacheAndServiceFails() {
        PercentageService percentageService = mock(PercentageService.class);
        CacheManager cacheManager = mock(CacheManager.class);

        when(percentageService.getCurrentPercentage()).thenThrow(new RuntimeException("Fallo"));
        when(cacheManager.getCache("percentage")).thenReturn(null);

        CalculationServiceImpl service = new CalculationServiceImpl(percentageService, cacheManager);

        CalculationRequest request = new CalculationRequest(1.0, 1.0);

        Exception exception = assertThrows(IllegalStateException.class, () ->
                service.calculateWithPercentage(request));

        assertTrue(exception.getMessage().contains("No se pudo obtener el porcentaje"));
    }
}
