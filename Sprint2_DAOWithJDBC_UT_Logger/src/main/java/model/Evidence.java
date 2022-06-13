package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

/**
 * Extends from AbstractEntity, having all instances from AbstractEntity.
 * Contains 7 common fields of a Evidence: CriminalCase, Storage, number, itemName, notes, archived, Set<TrackEntry>.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evidence extends AbstractEntity<Evidence>{

    /**
     * criminalCase: not null or blank.
     * Relationship: An evidence belongs to a criminal case, but one criminal case can have many evidences. [1 - 1].
     * usage: defined the criminal case this evidence belongs to.
     */
    private CriminalCase criminalCase;
    /**
     * storage: not null or blank.
     * Relationship: An evidence is found in a location. A location can have one or many evidences. [1 - 1].
     * usage: defined the location in which this evidence is found.
     */
    private Storage storage;
    /**
     * number: not null or blank, unique, distinct.
     * requirement: Following the Standard string rule in the AbstractEntity class. (Contains {5-30} characters)
     * usage: defined a unique number of the evidence?.
     */
    private String number;
    /**
     * itemName: not null or blank.
     * requirement: Following the Standard string rule in the AbstractEntity class. (Contains {5-30} characters)
     * usage: defined a name of the item in the location in which this evidence is found.
     */
    private String itemName;
    /**
     * notes: not null, may be blank "".
     * requirement: Following the Standard string rule in the AbstractEntity class. (Contains {0-200} characters)
     * usage: note something about this criminalCase.
     */
    private String notes;
    /**
     * archived: not null or blank.
     * usage: does the evidence belong to a case closed? if yes then archived is true else then archived is false.
     */
    private boolean archived;
    /**
     * trackEntrySet: not null or blank.
     * Relationship: An evidence can have one or many Track Entry. A track entry can only have an evidence. [1 - *].
     * usage: contains a set of track entry.
     */
    private Set<TrackEntry> trackEntrySet;

    public Evidence(long id) {
        super.setId(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Evidence evidence = (Evidence) o;
        return archived == evidence.archived && criminalCase.equals(evidence.criminalCase) && storage.equals(evidence.storage) && number.equals(evidence.number) && itemName.equals(evidence.itemName) && notes.equals(evidence.notes) && trackEntrySet.equals(evidence.trackEntrySet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), criminalCase, storage, number, itemName, notes, archived, trackEntrySet);
    }

    @Override
    public int compareTo(Evidence o) {
        return 0;
    }

    public void replaceWith(Evidence newEvidence) {
        super.setId(newEvidence.getId());
        super.setVersion(newEvidence.getVersion());
        super.setCreatedAt(newEvidence.getCreatedAt());
        super.setModifiedAt(newEvidence.getModifiedAt());

        this.storage = newEvidence.getStorage();
        this.number = newEvidence.getNumber();
        this.itemName = newEvidence.getItemName();
        this.notes = newEvidence.getNotes();
        this.archived = newEvidence.isArchived();

        this.criminalCase = newEvidence.getCriminalCase();
        this.trackEntrySet = newEvidence.getTrackEntrySet();
    }
}
