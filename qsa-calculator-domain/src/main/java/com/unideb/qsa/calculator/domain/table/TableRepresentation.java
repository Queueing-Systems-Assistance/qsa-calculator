package com.unideb.qsa.calculator.domain.table;

import java.util.List;

import com.unideb.qsa.calculator.domain.system.SystemElement;
import com.unideb.qsa.calculator.domain.system.SystemOutput;

/**
 * Represents a table response.
 */
public final class TableRepresentation {

    private final SystemElement systemElement;
    private final List<SystemOutput> systemOutputs;

    private TableRepresentation(TableRepresentation.Builder builder) {
        this.systemElement = builder.systemElement;
        this.systemOutputs = builder.systemOutputs;
    }

    public SystemElement getSystemElement() {
        return systemElement;
    }

    public List<SystemOutput> getSystemOutputs() {
        return systemOutputs;
    }

    /**
     * Builder for {@link TableRepresentation}.
     */
    public static class Builder {

        private SystemElement systemElement;
        private List<SystemOutput> systemOutputs;

        public TableRepresentation.Builder withName(SystemElement systemElement) {
            this.systemElement = systemElement;
            return this;
        }

        public TableRepresentation.Builder withSystemOutputs(List<SystemOutput> systemOutputs) {
            this.systemOutputs = systemOutputs;
            return this;
        }

        public TableRepresentation build() {
            return new TableRepresentation(this);
        }
    }
}
