package com.example.immigration.entities;
import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Applicant extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String passportNumber;
    private String nationality;
    private boolean criminalRecord;
    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL)
    @JsonIgnore

    private List<VisaApplication> visaApplications;
    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Interview> interviews;

    public Applicant() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public boolean isCriminalRecord() {
        return criminalRecord;
    }

    public void setCriminalRecord(boolean criminalRecord) {
        this.criminalRecord = criminalRecord;
    }

    public List<VisaApplication> getVisaApplications() {
        return visaApplications;
    }

    public void setVisaApplications(List<VisaApplication> visaApplications) {
        this.visaApplications = visaApplications;
    }

    public List<Interview> getInterviews() {
        return interviews;
    }

    public void setInterviews(List<Interview> interviews) {
        this.interviews = interviews;
    }
}