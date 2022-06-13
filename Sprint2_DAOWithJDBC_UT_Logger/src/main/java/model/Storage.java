package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

/**
 * Extends from AbstractEntity, having all instances from AbstractEntity.
 * Contains 3 common fields of a Storage: name, location, Set<Evidence>.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Storage extends AbstractEntity<Storage>{
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

    @Override
    public int compareTo(Storage o) {
        return 0;
    }

    public void replaceWith(Storage newStorage) {
        super.setId(newStorage.getId());
        super.setVersion(newStorage.getVersion());
        super.setCreatedAt(newStorage.getCreatedAt());
        super.setModifiedAt(newStorage.getModifiedAt());

        this.name = newStorage.getName();
        this.location = newStorage.getLocation();

        this.evidenceSet = newStorage.getEvidenceSet();
    }
}
