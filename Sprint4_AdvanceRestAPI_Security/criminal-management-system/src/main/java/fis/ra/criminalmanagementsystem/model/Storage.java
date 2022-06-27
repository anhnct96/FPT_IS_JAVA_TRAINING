package fis.ra.criminalmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "storage")
@AttributeOverride(name = "id", column = @Column(name = "storage_id"))
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Storage extends AbstractEntity {
    @Column(name = "storage_number", unique = true, columnDefinition = "varchar(255) default ''")
    private String name;
    @Column(name = "location", columnDefinition = "varchar(255) default ''")
    private String location;

    @OneToMany(mappedBy = "storage")
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Evidence> evidenceSet = new HashSet<>();

    public Storage() {
        super();
        this.name = "";
        this.location = "";
    }

    @Override
    public String toString() {
        return "Storage{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", id=" + id +
                ", version=" + version +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Storage storage)) return false;
        if (!super.equals(o)) return false;
        return name.equals(storage.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }
}