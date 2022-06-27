package fis.ra.criminalmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@Entity
@Table(name = "evidence")
@AttributeOverride(name = "id", column = @Column(name = "evidence_id"))
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
//@NamedQuery(name = "Evidence.findEvidenceByCriminalNumber", query = "select e from Evidence e, CriminalCase c where e.criminalCase.id = c.id and c.number = ?1")
public class Evidence extends AbstractEntity {

    @Column(name = "evidence_number", unique = true, columnDefinition = "varchar(255) default ''")
    @NotNull
    private String number;

    @Column(name = "item_name", columnDefinition = "varchar(255) default ''")
    private String itemName;

    //very big text
    @Column(name = "notes", columnDefinition = "varchar(4000) default ''")
    private String notes;

    @Column(name = "archived", columnDefinition = "bit(1) default 0")
    private Boolean archived;
    @OneToMany(mappedBy = "evidence", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIdentityReference(alwaysAsId = true)
    private Set<TrackEntry> trackEntries = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "criminal_case_id", foreignKey = @ForeignKey(name = "case_fk"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CriminalCase criminalCase;

    @ManyToOne
    @JoinColumn(name = "storage_id", foreignKey = @ForeignKey(name = "storage_fk"))
//    @JsonIgnore
    private Storage storage;

    public Evidence() {
        super();
        this.number = "";
        this.itemName = "";
        this.notes = "";
        this.archived = false;
    }

    @Override
    public String toString() {
        return "Evidence{" +
                "number='" + number + '\'' +
                ", itemName='" + itemName + '\'' +
                ", notes='" + notes + '\'' +
                ", archived=" + archived +
                ", id=" + id +
                ", version=" + version +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Evidence evidence)) return false;
        if (!super.equals(o)) return false;
        return number.equals(evidence.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), number);
    }

}
