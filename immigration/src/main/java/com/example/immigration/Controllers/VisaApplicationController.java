package com.example.immigration.Controllers;
import com.example.immigration.Entities.VisaApplication;
import com.example.immigration.Services.VisaApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/visas")
public class VisaApplicationController {
    @Autowired
    private VisaApplicationService visaApplicationService;

    @PostMapping("/submit/{applicantId}")
    public VisaApplication submitApplication(
            @PathVariable Long applicantId,
            @RequestParam String type) {
        return visaApplicationService.submitApplication(applicantId, type);
    }
    @PutMapping("/{visaId}/assign/{officerId}")
    public VisaApplication assignOfficer(
            @PathVariable Long visaId,
            @PathVariable Long officerId) {
        return visaApplicationService.assignOfficer(visaId, officerId);
    }

    @PutMapping("/{visaId}/process")
    public VisaApplication processVisa(
            @PathVariable Long visaId,
            @RequestParam String status,
            @RequestParam String notes) {
        return visaApplicationService.processVisa(visaId, status, notes);
    }

    @GetMapping("/applicant/{applicantId}")
    public List<VisaApplication> getVisasByApplicant(@PathVariable Long applicantId) {
        return visaApplicationService.getVisasByApplicant(applicantId);
    }

    @GetMapping("/status/{status}")
    public List<VisaApplication> getVisasByStatus(@PathVariable String status) {
        return visaApplicationService.getVisasByStatus(status);
    }
}
