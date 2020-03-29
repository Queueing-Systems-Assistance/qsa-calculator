package com.unideb.qsa.calculator.server.monitoring;


import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricSet;

/**
 * A set of gauges for CPU usage.
 */
public class CpuUsageGaugeSet implements MetricSet {

    private static final String CPU_AVAILABILITY = "processors.available";
    private static final String CPU_LOAD = "load.average";
    private static final String OPERATION_SYSTEM_VERSION = "version";
    private static final String OPERATION_SYSTEM_ARCHITECTURE = "arch";
    private static final String OPERATION_SYSTEM_NAME = "name";

    private final OperatingSystemMXBean mxBean;

    /**
     * Default constructor for metrics.
     */
    public CpuUsageGaugeSet() {
        this.mxBean = ManagementFactory.getOperatingSystemMXBean();
    }

    @Override
    public Map<String, Metric> getMetrics() {
        final Map<String, Metric> gauges = new HashMap<>();

        gauges.put(CPU_AVAILABILITY, (Gauge<Integer>) mxBean::getAvailableProcessors);
        gauges.put(OPERATION_SYSTEM_NAME, (Gauge<String>) mxBean::getName);
        gauges.put(OPERATION_SYSTEM_ARCHITECTURE, (Gauge<String>) mxBean::getArch);
        gauges.put(CPU_LOAD, (Gauge<Double>) mxBean::getSystemLoadAverage);
        gauges.put(OPERATION_SYSTEM_VERSION, (Gauge<String>) mxBean::getVersion);

        return Collections.unmodifiableMap(gauges);
    }
}
