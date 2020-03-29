package com.unideb.qsa.calculator.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;

import com.unideb.qsa.calculator.domain.system.SystemElement;
import com.unideb.qsa.calculator.domain.system.SystemInput;
import com.unideb.qsa.calculator.implementation.resolver.SystemElementResolver;
import com.unideb.qsa.calculator.implementation.resolver.SystemInputResolver;

/**
 * Controller for systems.
 */
@RestController
@RequestMapping("systems")
public class SystemController {

    @Autowired
    private SystemElementResolver systemElementResolver;
    @Autowired
    private SystemInputResolver systemInputResolver;

    /**
     * Mapping for system inputs.
     *
     * @param systemId as the systemID
     * @return the given system inputs
     */
    @GetMapping("/{systemId}")
    @Timed(name = "controller.get.feature", absolute = true)
    @ExceptionMetered(name = "exception.controller.feature", absolute = true)
    public List<SystemInput> getSystemInputs(@PathVariable String systemId) {
        return systemInputResolver.resolve(systemId);
    }

    /**
     * Mapping for systems.
     *
     * @return all the systems
     */
    @GetMapping
    @Timed(name = "controller.get.systems", absolute = true)
    @ExceptionMetered(name = "exception.controller.systems", absolute = true)
    public List<SystemElement> getSystems() {
        return systemElementResolver.resolve();
    }

}
