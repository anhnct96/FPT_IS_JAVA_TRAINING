package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.enums.CaseStatus;
import model.enums.CaseType;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CriminalCase that = (CriminalCase) o;
        return number.equals(that.number) && type == that.type && shortDescription.equals(that.shortDescription)
                && detailedDescription.equals(that.detailedDescription) && status == that.status
                && notes.equals(that.notes) && evidenceSet.equals(that.evidenceSet)
                && leadInvestigator.equals(that.leadInvestigator) && assigned.equals(that.assigned);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), number, type, shortDescription, detailedDescription, status, notes,
                evidenceSet, leadInvestigator, assigned);
    }

    @Override
    public int compareTo(CriminalCase o) {
        return 0;
    }

    @Override
    public String toString() {
        return "CriminalCase{" +
                "number='" + number + '\'' +
                ", type=" + type +
                ", shortDescription='" + shortDescription + '\'' +
                ", detailedDescription='" + detailedDescription + '\'' +
                ", status=" + status +
                ", notes='" + notes + '\'' +
                ", evidenceSet=" + evidenceSet +
                ", leadInvestigator=" + leadInvestigator +
                ", assigned=" + assigned +
                ", id=" + id +
                ", version=" + version +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                '}';
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
}
