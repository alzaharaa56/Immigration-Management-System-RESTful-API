package com.example.immigration.Services;
import com.example.immigration.Entities.Applicant;
import com.example.immigration.Entities.Interview;
import com.example.immigration.Repositories.ApplicantRepository;
import com.example.immigration.Repositories.InterviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ApplicantService {
    @Autowired
    private ApplicantRepository applicantRepository;
    @Autowired
    private InterviewRepository interviewRepository;

    public Applicant saveApplicant(Applicant applicant) {
        if (applicant.getPassportNumber() == null || applicant.getPassportNumber().isEmpty()) {
            throw new RuntimeException("Passport number is required!");
        }
        if (applicant.getFirstName() == null || applicant.getLastName() == null) {
            throw new RuntimeException("First name and Last name are required!");
        }
        return applicantRepository.save(applicant);
    }

    public Applicant saveApplicant(String firstName, String lastName, String passportNumber, String nationality) {
        Applicant applicant = new Applicant();
        applicant.setFirstName(firstName);
        applicant.setLastName(lastName);
        applicant.setPassportNumber(passportNumber);
        applicant.setNationality(nationality);
        return saveApplicant(applicant);
    }

    public Applicant flagCriminalRecord(Long applicantId) {
        Applicant applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new RuntimeException("Applicant not found"));
        applicant.setCriminalRecord(true);
        applicantRepository.save(applicant);

        List<Interview> interviews = interviewRepository.findByApplicantId(applicantId);
        for (Interview interview : interviews) {
            if ("SCHEDULED".equals(interview.getStatus())) {
                interview.setStatus("CANCELLED");
                interviewRepository.save(interview);
            }
        }
        return applicant;
    }

    public List<Applicant> getAllApplicants() {
        return applicantRepository.findAll();
    }

    public List<Applicant> getApplicantsByNationality(String nationality) {
        return applicantRepository.findByNationality(nationality);
    }

    public Applicant getApplicantById(Long id) {
        return applicantRepository.findById(id).orElse(null);
    }
}
