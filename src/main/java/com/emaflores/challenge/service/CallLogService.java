package com.emaflores.challenge.service;

import org.springframework.scheduling.annotation.Async;

public interface CallLogService {
    @Async
    void logCall(String endpoint, String parameters, String result, boolean error);
}
