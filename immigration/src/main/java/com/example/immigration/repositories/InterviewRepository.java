package com.example.immigration.repositories;
import com.example.immigration.entities.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface InterviewRepository extends JpaRepository<Interview, Long> {
    List<Interview> findByOfficerIdAndInterviewDate(Long officerId, String date);
    List<Interview> findByApplicantId(Long applicantId);
}
