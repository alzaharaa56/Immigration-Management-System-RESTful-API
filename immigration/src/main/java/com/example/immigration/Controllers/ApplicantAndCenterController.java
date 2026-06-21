package com.example.immigration.Controllers;
import com.example.immigration.Dtos.ApplicantDTO;
import com.example.immigration.Entities.Applicant;
import com.example.immigration.Entities.AsylumSeeker;
import com.example.immigration.Entities.ImmigrationCenter;
import com.example.immigration.Services.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api")
public class ApplicantAndCenterController {
    @Autowired
    private ApplicantService applicantService;

    @Autowired
    private com.example.immigration.Repositories.CenterRepository centerRepository;

    @PostMapping("/centers")
    public ImmigrationCenter createCenter(@RequestBody ImmigrationCenter center) {
        return centerRepository.save(center);
    }

    @GetMapping("/centers/{id}")
    public ImmigrationCenter getCenterById(@PathVariable Long id) {
        return centerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Center not found with ID: " + id));
    }

    @PostMapping("/applicants")
    public Applicant registerApplicant(@RequestBody Applicant applicant) {
        return applicantService.saveApplicant(applicant);
    }

    @PostMapping("/applicants/asylum")
    public AsylumSeeker registerAsylumSeeker(@RequestBody AsylumSeeker seeker) {
        return (AsylumSeeker) applicantService.saveApplicant(seeker);
    }

    @GetMapping("/applicants")
    public List<Applicant> getAllApplicants() {

        return applicantService.getApplicantsByNationality("");
    }

    @GetMapping("/applicants/search")
    public List<Applicant> searchByNationality(@RequestParam String nationality) {

        return applicantService.getApplicantsByNationality(nationality);
    }

    @PutMapping("/applicants/{id}/flag")
    public String flagCriminalRecord(@PathVariable Long id) {
        applicantService.flagCriminalRecord(id);
        return "Applicant with ID " + id + " has been flagged with a criminal record, and all scheduled interviews have been cancelled.";
    }

    @GetMapping("/applicants/dto")
    public List<ApplicantDTO> getAllApplicantsDTO() {
        return applicantService.getAllApplicants().stream().map(applicant ->
                new ApplicantDTO(
                        applicant.getFirstName(),
                        applicant.getLastName(),
                        applicant.getEmail(),
                        applicant.getNationality()
                )
        ).collect(java.util.stream.Collectors.toList());
    }
}
