package model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

/**
 * Extends from AbstractEntity, having all instances from AbstractEntity.
 * Contains 3 common fields of a Storage: name, location, Set<Evidence>.
 */
public class Storage extends AbstractEntity{
    /**
     * name: not null or blank.
     * requirement: Following the Standard string rule in the AbstractEntity class. (Contains {5-50} characters)
     * usage: defined a name of the storage.
     */
    private String name;
    /**
     * location: not null or blank.
     * requirement: Following the Standard string rule in the AbstractEntity class. (Contains {5-50} characters)
     * usage: defined the location.
     */
    private String location;
    /**
     * evidenceSet: not null or blank.
     * Relationship: One location can find one or many evidences. One evidence can only be found at a location. [1 - *]
     * usage: contains a set of evidence.
     */
    private Set<Evidence> evidenceSet;

    /**
     * Constructor with all instances.
     * @param id
     * @param version
     * @param createdAt
     * @param modifiedAt
     * @param name
     * @param location
     * @param evidenceSet
     */
    public Storage(long id, int version, LocalDateTime createdAt, LocalDateTime modifiedAt, String name, String location, Set<Evidence> evidenceSet) {
        super(id, version, createdAt, modifiedAt);
        this.name = name;
        this.location = location;
        this.evidenceSet = evidenceSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Evidence> getEvidenceSet() {
        return evidenceSet;
    }

    public void setEvidenceSet(Set<Evidence> evidenceSet) {
        this.evidenceSet = evidenceSet;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", evidenceSet=" + evidenceSet +
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
        Storage storage = (Storage) o;
        return name.equals(storage.name) && location.equals(storage.location) && evidenceSet.equals(storage.evidenceSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, location, evidenceSet);
    }
}
