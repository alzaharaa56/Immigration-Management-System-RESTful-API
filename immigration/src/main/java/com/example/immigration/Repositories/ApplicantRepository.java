package com.example.immigration.Repositories;
import com.example.immigration.Entities.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
    List<Applicant> findByNationality(String nationality);
}
