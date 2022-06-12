package model;

import model.enumerable.CaseStatus;
import model.enumerable.CaseType;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

/**
 * Extends from AbstractEntity, having all instances from AbstractEntity.
 * Contains 9 common fields of a CriminalCase: number, type, shortDescription, detailedDescription, status, notes, Set<Evidence>, leadInvestigator, Set<Detective>.
 */
public class CriminalCase extends AbstractEntity{
    /**
     * number: not null or blank, unique, distinct.
     * requirement: Following the Standard string rule in the AbstractEntity class. (Contains {5-30} characters)
     * usage: defined a unique number of criminal case?.
     */
    private String number;
    /**
     * type: not null or blank.
     * A CriminalCase type is classified in one of the following categories: (uncategorized, infraction, misdemeanor, felony).
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
     * A CriminalCase status is classified in one of the following categories: (submitted, under_investigation. in_court, closed, dismissed, cold).
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
     * Relationship: A CriminalCase can have one or many evidences. An evidence can only belong to a CriminalCase. [1 - *].
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

    /**
     * Constructor with full instances.
     * @param id
     * @param version
     * @param createdAt
     * @param modifiedAt
     * @param number
     * @param type
     * @param shortDescription
     * @param detailedDescription
     * @param status
     * @param notes
     * @param evidenceSet
     * @param leadInvestigator
     * @param assigned
     */
    public CriminalCase(long id, int version, LocalDateTime createdAt, LocalDateTime modifiedAt, String number, CaseType type, String shortDescription, String detailedDescription, CaseStatus status, String notes, Set<Evidence> evidenceSet, Detective leadInvestigator, Set<Detective> assigned) {
        super(id, version, createdAt, modifiedAt);
        this.number = number;
        this.type = type;
        this.shortDescription = shortDescription;
        this.detailedDescription = detailedDescription;
        this.status = status;
        this.notes = notes;
        this.evidenceSet = evidenceSet;
        this.leadInvestigator = leadInvestigator;
        this.assigned = assigned;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public CaseType getType() {
        return type;
    }

    public void setType(CaseType type) {
        this.type = type;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public CaseStatus getStatus() {
        return status;
    }

    public void setStatus(CaseStatus status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Set<Evidence> getEvidenceSet() {
        return evidenceSet;
    }

    public void setEvidenceSet(Set<Evidence> evidenceSet) {
        this.evidenceSet = evidenceSet;
    }

    public Detective getLeadInvestigator() {
        return leadInvestigator;
    }

    public void setLeadInvestigator(Detective leadInvestigator) {
        this.leadInvestigator = leadInvestigator;
    }

    public Set<Detective> getAssigned() {
        return assigned;
    }

    public void setAssigned(Set<Detective> assigned) {
        this.assigned = assigned;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CriminalCase that = (CriminalCase) o;
        return number.equals(that.number) && type == that.type && shortDescription.equals(that.shortDescription) && detailedDescription.equals(that.detailedDescription) && status == that.status && notes.equals(that.notes) && evidenceSet.equals(that.evidenceSet) && leadInvestigator.equals(that.leadInvestigator) && assigned.equals(that.assigned);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), number, type, shortDescription, detailedDescription, status, notes, evidenceSet, leadInvestigator, assigned);
    }
}
