package model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

/**
 * Extends from AbstractEntity, having all instances from AbstractEntity.
 * Contains 7 common fields of a Evidence: CriminalCase, Storage, number, itemName, notes, archived, Set<TrackEntry>.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
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

    public Evidence(long id, int version, LocalDateTime createdAt, LocalDateTime modifiedAt, CriminalCase criminalCase, Storage storage, String number, String itemName, String notes, boolean archived) {
        super(id, version, createdAt, modifiedAt);
        this.criminalCase = criminalCase;
        this.storage = storage;
        this.number = number;
        this.itemName = itemName;
        this.notes = notes;
        this.archived = archived;
    }
}
