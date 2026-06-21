package com.example.immigration.Services;
import com.example.immigration.Entities.ImmigrationCenter;
import com.example.immigration.Entities.ImmigrationOfficer;
import com.example.immigration.Repositories.CenterRepository;
import com.example.immigration.Repositories.OfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class OfficerService {
    @Autowired
    private OfficerRepository officerRepository;
    @Autowired
    private CenterRepository centerRepository;

    public ImmigrationOfficer saveOfficer(ImmigrationOfficer officer) {
        return officerRepository.save(officer);
    }

    public ImmigrationOfficer promoteOfficer(Long officerId, String newRank, int newClearanceLevel) {
        if (newClearanceLevel < 1 || newClearanceLevel > 5) {
            throw new RuntimeException("Clearance level must be between 1 and 5.");
        }
        ImmigrationOfficer officer = officerRepository.findById(officerId)
                .orElseThrow(() -> new RuntimeException("Officer not found"));
        officer.setRank(newRank);
        officer.setClearanceLevel(newClearanceLevel);
        return officerRepository.save(officer);
    }

    public ImmigrationOfficer transferOfficer(Long officerId, Long newCenterId) {
        ImmigrationOfficer officer = officerRepository.findById(officerId)
                .orElseThrow(() -> new RuntimeException("Officer not found"));
        ImmigrationCenter center = centerRepository.findById(newCenterId)
                .orElseThrow(() -> new RuntimeException("Center not found"));
        officer.setCenter(center);
        return officerRepository.save(officer);
    }

    public List<ImmigrationOfficer> findOfficersByRank(String rank) {
        return officerRepository.findByRank(rank);
    }

    public List<ImmigrationOfficer> findOfficersByRank(String rank, int minimumClearanceLevel) {
        List<ImmigrationOfficer> officersByRank = officerRepository.findByRank(rank);
        List<ImmigrationOfficer> filtered = new ArrayList<>();
        for (ImmigrationOfficer officer : officersByRank) {
            if (officer.getClearanceLevel() >= minimumClearanceLevel) {
                filtered.add(officer);
            }
        }
        return filtered;
    }

    public ImmigrationOfficer getOfficerById(Long id) {
        return officerRepository.findById(id).orElse(null);
    }
}
