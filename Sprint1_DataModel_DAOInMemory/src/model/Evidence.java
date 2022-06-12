package model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

/**
 * Extends from AbstractEntity, having all instances from AbstractEntity.
 * Contains 7 common fields of a Evidence: CriminalCase, Storage, number, itemName, notes, archived, Set<TrackEntry>.
 */
public class Evidence extends AbstractEntity{

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

    /**
     * Constructor with all instances.
     * @param id
     * @param version
     * @param createdAt
     * @param modifiedAt
     * @param criminalCase
     * @param storage
     * @param number
     * @param itemName
     * @param notes
     * @param archived
     * @param trackEntrySet
     */

    public Evidence(long id, int version, LocalDateTime createdAt, LocalDateTime modifiedAt, CriminalCase criminalCase, Storage storage, String number, String itemName, String notes, boolean archived, Set<TrackEntry> trackEntrySet) {
        super(id, version, createdAt, modifiedAt);
        this.criminalCase = criminalCase;
        this.storage = storage;
        this.number = number;
        this.itemName = itemName;
        this.notes = notes;
        this.archived = archived;
        this.trackEntrySet = trackEntrySet;
    }

    public CriminalCase getCriminalCase() {
        return criminalCase;
    }

    public void setCriminalCase(CriminalCase criminalCase) {
        this.criminalCase = criminalCase;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public Set<TrackEntry> getTrackEntrySet() {
        return trackEntrySet;
    }

    public void setTrackEntrySet(Set<TrackEntry> trackEntrySet) {
        this.trackEntrySet = trackEntrySet;
    }

    @Override
    public String toString() {
        return "Evidence{" +
                "criminalCase=" + criminalCase +
                ", storage=" + storage +
                ", number='" + number + '\'' +
                ", itemName='" + itemName + '\'' +
                ", notes='" + notes + '\'' +
                ", archived=" + archived +
                ", trackEntrySet=" + trackEntrySet +
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
        Evidence evidence = (Evidence) o;
        return archived == evidence.archived && criminalCase.equals(evidence.criminalCase) && storage.equals(evidence.storage) && number.equals(evidence.number) && itemName.equals(evidence.itemName) && notes.equals(evidence.notes) && trackEntrySet.equals(evidence.trackEntrySet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), criminalCase, storage, number, itemName, notes, archived, trackEntrySet);
    }
}
