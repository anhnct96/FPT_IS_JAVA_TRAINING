package model;

import lombok.*;
import model.enums.CaseStatus;
import model.enums.CaseType;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

/**
 * Extends from AbstractEntity, having all instances from AbstractEntity.
 * Contains 9 common fields of a CriminalCase: number, type, shortDescription, detailedDescription, status, notes,
 * Set<Evidence>, leadInvestigator, Set<Detective>.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString

public class CriminalCase extends AbstractEntity<CriminalCase>{
    /**
     * number: not null or blank, unique, distinct.
     * requirement: Following the Standard string rule in the AbstractEntity class. (Contains {5-30} characters)
     * usage: defined a unique number of criminal case?.
     */
    private String number;
    /**
     * type: not null or blank.
     * A CriminalCase type is classified in one of the following categories:
     *      (uncategorized, infraction, misdemeanor, felony).
     * usage: defined a type of the criminal case.
     */
    private CaseType type;
    /**
     * shortDescription: not null, may be blank "".
     * requirement: Following the Standard string rule in the AbstractEntity class. (Contains {0-100} characters)
     * usage: short describe, summarize, brief of the criminalCase.
     */
    private String shortDescription;
    /**
     * detailedDescription: not null, may be blank "".
     * requirement: Following the Standard string rule in the AbstractEntity class. (Contains {0-500} characters)
     * usage: describe the criminalCase in detail.
     */
    private String detailedDescription;
    /**
     * status: not null or blank.
     * A CriminalCase status is classified in one of the following categories:
     *      (submitted, under_investigation. in_court, closed, dismissed, cold).
     */
    private CaseStatus status;
    /**
     * notes: not null, may be blank "".
     * requirement: Following the Standard string rule in the AbstractEntity class. (Contains {0-200} characters)
     * usage: note something about this criminalCase.
     */
    private String notes;
    /**
     * evidenceSet: not null or blank.
     * Relationship: A CriminalCase can have one or many evidences.
     *      An evidence can only belong to a CriminalCase. [1 - *].
     * usage: contains a set of submitted evidences.
     */
    private Set<Evidence> evidenceSet;
    /**
     * notes: not null or blank.
     * Relationship: This special detective lead the investment of the CriminalCase. [1 - 1].
     * usage: defined the leader detective of the criminal case investment.
     */
    private Detective leadInvestigator;
    /**
     * note: can be null
     * Relationship: A criminal case can be invested by one (leaderInvestigator) or many detectives. [1 - 0..*]
     * usage: contains the set of assigned detectives
     */
    private Set<Detective> assigned;

    public CriminalCase(long id, int version, LocalDateTime createdAt, LocalDateTime modifiedAt, String number, CaseType type, String shortDescription, String detailedDescription, CaseStatus status, String notes, Detective leadInvestigator) {
        super(id, version, createdAt, modifiedAt);
        this.number = number;
        this.type = type;
        this.shortDescription = shortDescription;
        this.detailedDescription = detailedDescription;
        this.status = status;
        this.notes = notes;
        this.leadInvestigator = leadInvestigator;
    }

    public void replaceWith(CriminalCase newCriminalCase){
        super.setId(newCriminalCase.getId());
        super.setVersion(newCriminalCase.getVersion());
        super.setCreatedAt(newCriminalCase.getCreatedAt());
        super.setModifiedAt(newCriminalCase.getModifiedAt());

        this.number = newCriminalCase.getNumber();
        this.type = newCriminalCase.getType();
        this.shortDescription = newCriminalCase.getShortDescription();
        this.detailedDescription = newCriminalCase.getDetailedDescription();
        this.status = newCriminalCase.getStatus();
        this.notes = newCriminalCase.getNotes();

        this.evidenceSet = newCriminalCase.getEvidenceSet();
        this.leadInvestigator = newCriminalCase.getLeadInvestigator();
        this.assigned = newCriminalCase.getAssigned();
    }

    @Override
    public int compareTo(CriminalCase o) {
        return 0;
    }
}
