package fis.ra.criminalmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;
import fis.ra.criminalmanagementsystem.core.EmploymentStatus;
import fis.ra.criminalmanagementsystem.core.Rank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "detective")
@AttributeOverride(name = "id", column = @Column(name = "detective_id"))
@SuperBuilder
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Detective extends AbstractEntity {

    @Column(name = "username", unique = true, columnDefinition = "varchar(255) default ''")
    @NotNull
    @NotEmpty(message = "No username")
    private String username;

    @Column(name = "first_name", columnDefinition = "varchar(255) default ''")
    private String firstName;

    @Column(name = "last_name", columnDefinition = "varchar(255) default ''")
    private String lastName;

    @Column(name = "passwords", columnDefinition = "varchar(255) default ''")
    @NotNull
    @NotEmpty(message = "No password")
    private String password;

    @Column(name = "hiring_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime hiringDate;

    @Column(name = "badge_number", unique = true, columnDefinition = "varchar(255) default ''")
    @NotNull
    @NotEmpty(message = "No badge number")
    private String badgeNumber;

    @Column(name = "detective_rank", length = 30, columnDefinition = "varchar(30) default 'TRAINEE'")
    @Enumerated(EnumType.STRING)
    private Rank rank;

    @Column(name = "armed", columnDefinition = "bit(1) default 0")
    private boolean armed;

    @Column(name = "detective_status", length = 30, columnDefinition = "varchar(30) default 'ACTIVE'")
    @Enumerated(EnumType.STRING)
    private EmploymentStatus status;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToMany
    @JoinTable(
            name = "working_detective_case",
            joinColumns =
            @JoinColumn(
                    name = "detective_id",
                    foreignKey = @ForeignKey(name = "detective_id_fk"),
                    nullable = false,
                    updatable = false
            ),
            inverseJoinColumns =
            @JoinColumn(
                    name = "criminal_case_id",
                    foreignKey = @ForeignKey(name = "case_id_fk"),
                    nullable = false,
                    updatable = false
            )
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<CriminalCase> criminalCases;

    @OneToMany(mappedBy = "detective")
    @JsonIdentityReference(alwaysAsId = true)
    private Set<TrackEntry> trackEntries;

    public Detective() {
        this.username = "";
        this.firstName = "";
        this.lastName = "";
        this.password = "";
        this.hiringDate = LocalDateTime.now();
        this.badgeNumber = "";
        this.rank = Rank.TRAINEE;
        this.armed = false;
        this.status = EmploymentStatus.ACTIVE;
    }

    @Override
    public String toString() {
        return "Detective{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", hiringDate=" + hiringDate +
                ", badgeNumber='" + badgeNumber + '\'' +
                ", rank=" + rank +
                ", armed=" + armed +
                ", status=" + status +
                ", id=" + id +
                ", version=" + version +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Detective detective)) return false;
        if (!super.equals(o)) return false;
        return badgeNumber.equals(detective.badgeNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), badgeNumber);
    }
}
