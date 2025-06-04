package com.emaflores.challenge.controller;

import com.emaflores.challenge.model.CallLog;
import com.emaflores.challenge.repository.CallLogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/logs")
public class CallLogController {

    private final CallLogRepository repository;

    public CallLogController(CallLogRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Page<CallLog> getLogs(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size) {
        return repository.findAll(PageRequest.of(page, size, Sort.by("timestamp").descending()));
    }
}

