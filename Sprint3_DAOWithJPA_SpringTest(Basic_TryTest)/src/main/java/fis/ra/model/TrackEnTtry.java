package fis.ra.model;

import com.sun.istack.NotNull;
import fis.ra.core.CaseStatus;
import fis.ra.core.CaseType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "criminal_case")
@AttributeOverride(name = "id", column = @Column(name = "criminal_case_id"))
public class TrackEnTtry extends  AbstractEntity{

    @Column(name = "criminal_case_number", unique = true, columnDefinition = "varchar(255) default ''")
    @NotNull
    private String number;
    @Column(name = "type", length = 30, columnDefinition = "varchar(30) default 'UNCATEGORIZED'")
    @Enumerated(EnumType.STRING)
    private CaseType type;
    @Column(name = "short_description", columnDefinition = "varchar(255) default ''")
    private String shortDescription;

    @Column(name = "detailed_description")
    @Lob
    private String detailedDescription;
    @Column(name = "case_status", length = 30, columnDefinition = "varchar(30) default 'SUBMITTED'")
    @Enumerated(EnumType.STRING)
    private CaseStatus status;
    //very big text
    @Column(name = "notes")
    @Lob
    private String notes;

    @OneToMany(mappedBy = "criminalCase")
    private Set<Evidence> evidenceSet = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "lead_investigator_id")
    private Detective leadInvestigator;
    @ManyToMany
    @JoinTable(
            name = "working_detective_case",
            joinColumns = @JoinColumn(name = "criminal_case_id"),
            inverseJoinColumns = @JoinColumn(name = "detective_id"))
    private Set<Detective> assigned = new HashSet<>();


    public TrackEnTtry() {
        super();
        shortDescription = "";
        this.status = CaseStatus.SUBMITTED;
        this.type = CaseType.UNCATEGORIZED;
    }

    public void setEvidenceSet(Set<Evidence> evidenceSetArg) {
        evidenceSetArg.forEach(this :: addEvidence);
    }

    public boolean addEvidence(Evidence evidence) {
        evidence.setCriminalCase(this);
        return evidenceSet.add(evidence);
    }

    public void setAssigned(Set<Detective> assignedArg) {
        assignedArg.forEach(this:: addDetective);
    }

    //case is assigned to the detective, always use this method
    public boolean addDetective(Detective detective) {
        detective.addCase(this);
        return assigned.add(detective);
    }
}
