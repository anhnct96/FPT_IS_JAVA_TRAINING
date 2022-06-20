package fis.ra.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.sun.istack.NotNull;
import fis.ra.core.EmploymentStatus;
import fis.ra.core.Rank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "detective")
@AttributeOverride(name = "id", column = @Column(name = "detective_id"))
public class Detective extends AbstractEntity {

    @Column(name = "username", unique = true, columnDefinition = "varchar(255) default ''")
    @NotNull
    private String username;
    @Column(name = "first_name", columnDefinition = "varchar(255) default ''")
    private String firstName;
    @Column(name = "last_name", columnDefinition = "varchar(255) default ''")
    private String lastName;
    @Column(name = "passwords", columnDefinition = "varchar(255) default ''")
    private String password;
    @Column(name = "hiring_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime hiringDate;
    @Column(name = "badge_number", unique = true, columnDefinition = "varchar(255) default ''")
    private String badgeNumber;
    @Column(name = "detective_rank", length = 30, columnDefinition = "varchar(30) default 'TRAINEE'")
    @Enumerated(EnumType.STRING)
    private Rank rank;
    @Column(name = "armed", columnDefinition = "smallint default 0")
    private Boolean armed = false;
    @Column(name = "detective_status", length = 30, columnDefinition = "varchar(30) default 'ACTIVE'")
    @Enumerated(EnumType.STRING)
    private EmploymentStatus status = EmploymentStatus.ACTIVE;

    @ManyToMany(mappedBy = "assigned")
    private Set<TrackEnTtry> criminalCases = new HashSet<>();

    @OneToMany(mappedBy = "detective")
    private Set<TrackEntry> trackEntries;

    boolean addCase(TrackEnTtry criminalCase){
        return criminalCases.add(criminalCase);
    }

    public boolean addTrackEntry(TrackEntry trackEntry){
        trackEntry.setDetective(this);
        return trackEntries.add(trackEntry);
    }

    @Override
    public String toString() {
        return String.format("Detective\n\t[abstractEntity='%s', username='%s', firstName='%s', lastName='%s', password='%s', hiringDate='%s', badgeNumber='%s', rank='%s', armed='%s', status='%s']",
                username, firstName, lastName, password, hiringDate == null? "" : hiringDate.toString(), badgeNumber,rank, armed, status);
    }
}
