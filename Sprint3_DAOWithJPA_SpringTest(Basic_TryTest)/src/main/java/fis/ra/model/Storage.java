package fis.ra.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "storage")
@AttributeOverride(name = "id", column = @Column(name = "storage_id"))
public class Storage extends AbstractEntity {
    @Column(name = "storage_number", unique = true, columnDefinition = "varchar(255) default ''")
    private String name;
    @Column(name = "location", columnDefinition = "varchar(255) default ''")
    private String location;

    @OneToMany(mappedBy = "storage")
    private Set<Evidence> evidenceSet = new HashSet<>();

    public void setEvidenceSet(Set<Evidence> evidenceSetArg) {
        evidenceSetArg.forEach(this::addEvidence);
    }

    public boolean addEvidence(Evidence evidence) {
        evidence.setStorage(this);
        return evidenceSet.add(evidence);
    }

    @Override
    public String toString() {
        return String.format("Storage[id='%d%n', name='%s', location='%s', version='%d%n']",
                id, name, location, version);
    }
}