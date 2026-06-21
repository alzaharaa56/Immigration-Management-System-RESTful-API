package com.example.immigration.Repositories;
import com.example.immigration.Entities.ImmigrationCenter;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CenterRepository extends JpaRepository<ImmigrationCenter, Long> {
}
