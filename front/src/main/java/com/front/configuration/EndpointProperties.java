package com.front.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "endpoint")
public class EndpointProperties {

    private String patientUri;
    private String noteUri;

    public String getPatientUri() {
        return patientUri;
    }

    public void setPatientUri(String patientUri) {
        this.patientUri = patientUri;
    }

    public String getNoteUri() {
        return noteUri;
    }

    public void setNoteUri(String noteUri) {
        this.noteUri = noteUri;
    }
}