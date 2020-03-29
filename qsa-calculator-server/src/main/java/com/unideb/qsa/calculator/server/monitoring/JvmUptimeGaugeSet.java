package com.unideb.qsa.calculator.server.monitoring;

import java.lang.management.ManagementFactory;
import java.util.Map;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricSet;

/**
 * A MetricSet to report JVM uptime.
 */
public class JvmUptimeGaugeSet implements MetricSet {

    private static final String UPTIME = "uptime";

    @Override
    public Map<String, Metric> getMetrics() {
        return Map.of(UPTIME, (Gauge<Long>) () -> ManagementFactory.getRuntimeMXBean().getUptime());
    }
}
