package com.example.immigration.Controllers;
import com.example.immigration.Entities.BorderControlOfficer;
import com.example.immigration.Entities.ImmigrationOfficer;
import com.example.immigration.Services.OfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/officers")
public class OfficerController {
    @Autowired
    private OfficerService officerService;

    @PostMapping
    public ImmigrationOfficer hireOfficer(@RequestBody ImmigrationOfficer officer) {

        return officerService.saveOfficer(officer);
    }

    @PostMapping("/border")
    public BorderControlOfficer hireBorderOfficer(@RequestBody BorderControlOfficer officer) {
        return (BorderControlOfficer) officerService.saveOfficer(officer);
    }

    @GetMapping("/{id}")
    public ImmigrationOfficer getOfficerDetails(@PathVariable Long id) {
        return officerService.getOfficerById(id);
    }

    @PutMapping("/{id}/promote")
    public ImmigrationOfficer promoteOfficer(
            @PathVariable Long id,
            @RequestParam String rank,
            @RequestParam int clearance) {
        return officerService.promoteOfficer(id, rank, clearance);
    }

    @PutMapping("/{id}/transfer/{centerId}")
    public ImmigrationOfficer transferOfficer(
            @PathVariable Long id,
            @PathVariable Long centerId) {
        return officerService.transferOfficer(id, centerId);
    }
}
