package fis.ra.criminalmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;
import fis.ra.criminalmanagementsystem.core.CaseStatus;
import fis.ra.criminalmanagementsystem.core.CaseType;
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
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "criminal_case")
@AttributeOverride(name = "id", column = @Column(name = "criminal_case_id"))
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
//@NamedQueries({
//        @NamedQuery(name = "CriminalCase.findByStatus", query = "select c from CriminalCase c where c.status = ?1"),
//        @NamedQuery(name = "CriminalCase.findByUsername", query = "select c from CriminalCase c join Detective d where c.assigned.id = d.id and d.username = ?1")
//})
public class CriminalCase extends AbstractEntity {

    @Column(name = "case_number", length = 100, unique = true, columnDefinition = "varchar(100) default ''")
    @NotNull
    private String number;

    @Column(name = "case_type", length = 30, columnDefinition = "varchar(30) default 'UNCATEGORIZED'")
    @Enumerated(EnumType.STRING)
    private CaseType type;

    @Column(name = "short_description", columnDefinition = "varchar(255) default ''")
    private String shortDescription;

    @Column(name = "detailed_description", columnDefinition = "varchar(4000) default ''")
    //@Lob
    private String detailedDescription;

    @Column(name = "case_status", length = 30, columnDefinition = "varchar(30) default 'UNCATEGORIZED'")
    @Enumerated(EnumType.STRING)
    private CaseStatus status;

    @Column(name = "notes", columnDefinition = "varchar(255) default ''")
    //@Lob
    private String notes;

    @OneToMany(mappedBy = "criminalCase")//, cascade = CascadeType.PERSIST)
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Evidence> evidenceSet = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "lead_investigator_id", foreignKey = @ForeignKey(name = "lead_investigator_fk"))
    private Detective leadInvestigator;

    @ManyToMany
    @JsonIdentityReference(alwaysAsId = true)
    @JoinTable(
            name = "working_detective_case",
            joinColumns =
            @JoinColumn(
                    name = "criminal_case_id",
                    foreignKey = @ForeignKey(name = "case_id_fk"),
                    nullable = false,
                    updatable = false
            ),
            inverseJoinColumns =
            @JoinColumn(
                    name = "detective_id",
                    foreignKey = @ForeignKey(name = "detective_id_fk"),
                    nullable = false,
                    updatable = false
            )
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Detective> assigned = new HashSet<>();

    public CriminalCase() {
        super();
        this.number = "";
        this.type = CaseType.UNCATEGORIZED;
        this.shortDescription = "";
        this.detailedDescription = "";
        this.status = CaseStatus.UNCATEGORIZED;
        this.notes = "";
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
                ", id=" + id +
                ", version=" + version +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CriminalCase that)) return false;
        if (!super.equals(o)) return false;
        return number.equals(that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), number);
    }


}
