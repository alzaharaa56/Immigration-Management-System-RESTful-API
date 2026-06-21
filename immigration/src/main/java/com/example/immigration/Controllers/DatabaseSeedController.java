package com.example.immigration.Controllers;
import com.example.immigration.Entities.*;
import com.example.immigration.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api")
public class DatabaseSeedController {
    @Autowired
    private CenterRepository centerRepository;
    @Autowired
    private OfficerRepository officerRepository;
    @Autowired
    private ApplicantRepository applicantRepository;
    @PostMapping("/seed")
    public String seedDatabase() {

        ImmigrationCenter center1 = new ImmigrationCenter();
        center1.setName("Muscat Embassy Center");
        center1.setLocationCountry("Oman");
        center1.setType("Embassy");
        center1.setDailyCapacity(100);
        centerRepository.save(center1);

        ImmigrationCenter center2 = new ImmigrationCenter();
        center2.setName("Al-Wajajah Border Control");
        center2.setLocationCountry("Oman");
        center2.setType("Border");
        center2.setDailyCapacity(500);
        centerRepository.save(center2);

        ImmigrationOfficer officer1 = new ImmigrationOfficer();
        officer1.setFirstName("Ahmed");
        officer1.setLastName("Al-Busaidi");
        officer1.setGender("Male");
        officer1.setBadgeNumber("OFF-101");
        officer1.setRank("Senior Officer");
        officer1.setClearanceLevel(5);
        officer1.setActive(true);
        officer1.setCenter(center1);
        officerRepository.save(officer1);

        ImmigrationOfficer officer2 = new ImmigrationOfficer();
        officer2.setFirstName("Ali");
        officer2.setLastName("Al-Hasni");
        officer2.setGender("Male");
        officer2.setBadgeNumber("OFF-102");
        officer2.setRank("Junior Officer");
        officer2.setClearanceLevel(2);
        officer2.setActive(true);
        officer2.setCenter(center1);
        officerRepository.save(officer2);
        BorderControlOfficer borderOfficer = new BorderControlOfficer();
        borderOfficer.setFirstName("Fatma");
        borderOfficer.setLastName("Al-Balushi");
        borderOfficer.setGender("Female");
        borderOfficer.setBadgeNumber("BRD-501");
        borderOfficer.setRank("Border Inspector");
        borderOfficer.setClearanceLevel(3);
        borderOfficer.setActive(true);
        borderOfficer.setCenter(center2);
        borderOfficer.setAssignedCheckpoint("Gate A");
        borderOfficer.setK9UnitAssigned(true);
        officerRepository.save(borderOfficer);

        Applicant app1 = new Applicant();
        app1.setFirstName("John");
        app1.setLastName("Doe");
        app1.setGender("Male");
        app1.setPassportNumber("A1234567");
        app1.setNationality("Canadian");
        app1.setCriminalRecord(false);
        applicantRepository.save(app1);
        Applicant app2 = new Applicant();
        app2.setFirstName("Jane");
        app2.setLastName("Smith");
        app2.setGender("Female");
        app2.setPassportNumber("B7654321");
        app2.setNationality("British");
        app2.setCriminalRecord(true);
        applicantRepository.save(app2);
        AsylumSeeker seeker = new AsylumSeeker();
        seeker.setFirstName("Samir");
        seeker.setLastName("Ahmad");
        seeker.setGender("Male");
        seeker.setPassportNumber("C9876543");
        seeker.setNationality("Syrian");
        seeker.setCriminalRecord(false);
        seeker.setCountryOfOrigin("Syria");
        seeker.setSponsorOrganization("UNHCR");
        applicantRepository.save(seeker);
        Applicant app3 = new Applicant();
        app3.setFirstName("Amara");
        app3.setLastName("Silva");
        app3.setGender("Female");
        app3.setPassportNumber("D5544332");
        app3.setNationality("Sri Lankan");
        app3.setCriminalRecord(false);
        applicantRepository.save(app3);
        return "Database seeded successfully!";
    }
}
