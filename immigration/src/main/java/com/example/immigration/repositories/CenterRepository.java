package com.example.immigration.repositories;
import com.example.immigration.entities.ImmigrationCenter;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CenterRepository extends JpaRepository<ImmigrationCenter, Long> {
}
