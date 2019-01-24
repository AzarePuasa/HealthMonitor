package com.azare.app.healthmonitor.model;

public enum ApptLocations {
    SGH("Singapore General Hospital"),
    NUH("National University Hospital"),
    NTFH("Ng Teng Fong Hospital"),
    KTPH("Khoo Teck Phuat Hospital"),
    JRP("Jurong Polyclinic"),
    BBP("Bukit Batok Polyclinic");

    private String full;

    ApptLocations(String full) {
        this.full = full;
    }
}
