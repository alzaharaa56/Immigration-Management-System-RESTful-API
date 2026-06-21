package com.example.immigration.Services;
import com.example.immigration.Entities.Applicant;
import com.example.immigration.Entities.ImmigrationOfficer;
import com.example.immigration.Entities.Interview;
import com.example.immigration.Repositories.ApplicantRepository;
import com.example.immigration.Repositories.InterviewRepository;
import com.example.immigration.Repositories.OfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class InterviewService {
    @Autowired
    private InterviewRepository interviewRepository;
    @Autowired
    private ApplicantRepository applicantRepository;
    @Autowired
    private OfficerRepository officerRepository;
    public Interview scheduleInterview(Long applicantId, Long officerId, String date) {

        List<Interview> doubleBookingCheck = interviewRepository.findByOfficerIdAndInterviewDate(officerId, date);
        if (!doubleBookingCheck.isEmpty()) {
            throw new RuntimeException("Officer is double-booked!");
        }
        Applicant applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new RuntimeException("Applicant not found"));
        ImmigrationOfficer officer = officerRepository.findById(officerId)
                .orElseThrow(() -> new RuntimeException("Officer not found"));
        Interview interview = new Interview();
        interview.setApplicant(applicant);
        interview.setOfficer(officer);
        interview.setInterviewDate(date);
        interview.setStatus("SCHEDULED");
        interview.setPurpose("Visa Review Interview");
        return interviewRepository.save(interview);
    }
    public Interview completeInterview(Long interviewId) {
        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new RuntimeException("Interview not found"));
        interview.setStatus("COMPLETED");
        return interviewRepository.save(interview);
    }
    public Interview cancelInterview(Long interviewId) {
        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new RuntimeException("Interview not found"));
        interview.setStatus("CANCELLED");
        return interviewRepository.save(interview);
    }
    public List<Interview> getOfficerSchedule(Long officerId, String date) {
        return interviewRepository.findByOfficerIdAndInterviewDate(officerId, date);
    }
}
