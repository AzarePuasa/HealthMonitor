package com.azare.app.healthmonitor.model;

public enum APPTTYPE {
    UPCOMING("Appointments that are upcoming."),
    COMPLETED("Appointments that have been completed.");

    String description;

    APPTTYPE(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
