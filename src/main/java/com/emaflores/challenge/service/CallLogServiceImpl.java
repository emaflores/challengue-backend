package com.emaflores.challenge.service;

import com.emaflores.challenge.model.CallLog;
import com.emaflores.challenge.repository.CallLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CallLogServiceImpl implements CallLogService{

    private final CallLogRepository repository;

    public CallLogServiceImpl (CallLogRepository repository){
        this.repository = repository;
    }

    @Override
    public void logCall (String endpoint, String parameters, String result, boolean error){
        CallLog log = new CallLog();
        log.setTimestamp(LocalDateTime.now());
        log.setEndpoint(endpoint);
        log.setParameters(parameters);
        log.setResult(result);
        log.setError(error);
        repository.save(log);

    }
}
