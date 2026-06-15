package com.example.immigration.entities;
import jakarta.persistence.*;
@Entity
public class VisaApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;
    @ManyToOne
    @JoinColumn(name = "officer_id")
    private ImmigrationOfficer handlingOfficer;
    private String visaType;
    private String status;
    private String officerNotes;

    public VisaApplication() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public ImmigrationOfficer getHandlingOfficer() {
        return handlingOfficer;
    }

    public void setHandlingOfficer(ImmigrationOfficer handlingOfficer) {
        this.handlingOfficer = handlingOfficer;
    }

    public String getVisaType() {
        return visaType;
    }

    public void setVisaType(String visaType) {
        this.visaType = visaType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOfficerNotes() {
        return officerNotes;
    }

    public void setOfficerNotes(String notes) {
        this.officerNotes = notes;
    }
}