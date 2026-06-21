package com.example.immigration.Repositories;
import com.example.immigration.Entities.ImmigrationOfficer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface OfficerRepository extends JpaRepository<ImmigrationOfficer, Long> {
    List<ImmigrationOfficer> findByRank(String rank);
}
