package com.unideb.qsa.calculator.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.unideb.qsa.calculator.domain.system.SystemOutput;
import com.unideb.qsa.calculator.domain.system.SystemOutputRequest;
import com.unideb.qsa.calculator.implementation.service.OutputFeatureService;

/**
 * Controller for system output features.
 */
@RestController
public class OutputFeatureController {

    @Autowired
    private OutputFeatureService outputFeatureService;

    /**
     * Resolves system outputs based on the requested ids.
     *
     * @return the calculated system outputs
     */
    @PostMapping("system/{systemId}/outputs")
    public List<SystemOutput> getOutputs(
            @PathVariable String systemId,
            @RequestBody SystemOutputRequest systemOutputRequest) {
        return outputFeatureService.getSystemOutputs(systemId, systemOutputRequest.getFeatures(), systemOutputRequest.getOutputFeatureIds());
    }

}
