package com.unideb.qsa.calculator.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.unideb.qsa.calculator.domain.calculator.SystemElement;
import com.unideb.qsa.calculator.implementation.service.SystemElementService;

/**
 * Controller for systems.
 */
@RestController
public class SystemElementController {

    @Autowired
    private SystemElementService systemElementService;

    /**
     * Mapping for system elements.
     * @param systemIds as the systemIDs
     * @return the given system elements
     */
    @PostMapping("system")
    public List<SystemElement> getSystemElements(@RequestBody List<String> systemIds) {
        return systemElementService.getSystemElements(systemIds);
    }
}
