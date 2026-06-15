package com.example.immigration.entities;
import jakarta.persistence.Entity;
@Entity
public class AsylumSeeker extends Applicant {
    private String countryOfOrigin;
    private String sponsorOrganization;

    public AsylumSeeker() {
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getSponsorOrganization() {
        return sponsorOrganization;
    }

    public void setSponsorOrganization(String sponsorOrganization) {
        this.sponsorOrganization = sponsorOrganization;
    }
}
