package com.example.immigration.Entities;
import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class ImmigrationOfficer extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String badgeNumber;
    @Column(name = "officer_rank")
    private String rank;
    private int clearanceLevel;
    private boolean active;
    @ManyToOne
    @JoinColumn(name = "center_id")


    private ImmigrationCenter center;
    @OneToMany(mappedBy = "officer")
    @JsonIgnore
    private List<Interview> interviews;

    public ImmigrationOfficer() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBadgeNumber() {
        return badgeNumber;
    }

    public void setBadgeNumber(String badgeNumber) {
        this.badgeNumber = badgeNumber;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getClearanceLevel() {
        return clearanceLevel;
    }

    public void setClearanceLevel(int clearanceLevel) {
        this.clearanceLevel = clearanceLevel;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ImmigrationCenter getCenter() {
        return center;
    }

    public void setCenter(ImmigrationCenter center) {
        this.center = center;
    }

    public List<Interview> getInterviews() {
        return interviews;
    }

    public void setInterviews(List<Interview> interviews) {
        this.interviews = interviews;
    }
}