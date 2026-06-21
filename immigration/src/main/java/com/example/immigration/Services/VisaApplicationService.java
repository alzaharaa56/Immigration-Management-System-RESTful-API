package com.example.immigration.Services;
import com.example.immigration.Entities.Applicant;
import com.example.immigration.Entities.ImmigrationOfficer;
import com.example.immigration.Entities.VisaApplication;
import com.example.immigration.Repositories.ApplicantRepository;
import com.example.immigration.Repositories.OfficerRepository;
import com.example.immigration.Repositories.VisaApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class VisaApplicationService {
    @Autowired
    private VisaApplicationRepository visaApplicationRepository;
    @Autowired
    private ApplicantRepository applicantRepository;
    @Autowired
    private OfficerRepository officerRepository;

    public VisaApplication submitApplication(Long applicantId, String visaType) {
        Applicant applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new RuntimeException("Applicant not found"));
        VisaApplication application = new VisaApplication();
        application.setApplicant(applicant);
        application.setVisaType(visaType);
        if (applicant.isCriminalRecord()) {
            application.setStatus("REJECTED");
            application.setOfficerNotes("Auto-rejected due to criminal flag.");
        } else {
            application.setStatus("PENDING");
        }
        return visaApplicationRepository.save(application);
    }

    public VisaApplication assignOfficer(Long visaId, Long officerId) {
        VisaApplication application = visaApplicationRepository.findById(visaId)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        ImmigrationOfficer officer = officerRepository.findById(officerId)
                .orElseThrow(() -> new RuntimeException("Officer not found"));
        if ("Asylum".equalsIgnoreCase(application.getVisaType())) {
            if (officer.getClearanceLevel() < 4) {
                throw new RuntimeException("Officer clearance level must be 4 or 5 for Asylum cases!");
            }
        }
        application.setHandlingOfficer(officer);
        return visaApplicationRepository.save(application);
    }

    public VisaApplication processVisa(Long visaId, String newStatus, String notes) {
        VisaApplication application = visaApplicationRepository.findById(visaId)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        application.setStatus(newStatus);
        application.setOfficerNotes(notes);
        return visaApplicationRepository.save(application);
    }

    public List<VisaApplication> getVisasByApplicant(Long applicantId) {
        return visaApplicationRepository.findByApplicantId(applicantId);
    }

    public List<VisaApplication> getVisasByStatus(String status) {
        return visaApplicationRepository.findByStatus(status);
    }
}
