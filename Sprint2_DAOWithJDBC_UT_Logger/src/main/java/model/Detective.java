package model;

import lombok.*;
import model.enums.EmploymentStatus;
import model.enums.Rank;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

/**
 * Extends from AbstractEntity, thus, a detective has all instances from AbstractEntity.
 * Contains 7 common fields of a Detective: Person, BadgeNumber, Rank, Armed, EmploymentStatus, Set<CriminalCase>, Set<TrackEntry>.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Detective extends AbstractEntity<Detective>{
    /**
     * userName: not null or blank, unique, distinct.
     * usage: defined a username to log in to the system of a person.
     */
    private String username;
    /**
     * firstName: not null or blank.
     * usage: contains person's first name.
     * requirement: firstName contains at least 5 and at most 20 characters, only  accept a-z, A-Z and the spaces!
     * accepted: nguyen, Nguyen, NGUYEN, Nguyen      ,     Nguyen    , NgUyEn   , Nguyen Cong.
     * standardize before updating/creating to the database.
     * standard firstName rule:
     *      Following the Standard string rule in the AbstractEntity class.
     *      First character of firstName must be uppercase character.
     */
    private String firstName;
    /**
     * lastName: not null or blank.
     * usage: contains person's last name.
     * requirement: lastName contains at least 5 and at most 20 characters, only  accept a-z, A-Z and the spaces!
     * accepted: Thao, thao, THAO, Thao      ,     Thao       , ThAO.
     * standardize before updating/creating to the database.
     * standard lastName rule:
     *      Following the Standard string rule in the AbstractEntity class.
     *      First character of lastName must be uppercase character.
     */
    private String lastName;
    /**
     * password: not null or blank.
     * requirement: password contains at least 6 characters.
     */
    private String password;
    /**
     * hiringDate: not null or blank.
     * usage: contains hiring date of a person.
     */
    private LocalDateTime hiringDate;
    /**
     * badeNumber: not null or blank, unique, distinct.
     * Relationship: A Detective has a BadgeNumber, a BadgeNumber is assigned for only one Detective. [1 - 1].
     * requirement: Following the Standard string rule in the AbstractEntity class. (Contains {5-30} characters)
     * usage: defined a unique badge number of a detective.
     */
    private String badgeNumber;
    /**
     * Rank: not null or blank.
     * A detective has a rank from Rank enum (trainee, junior, senior, inspector, chief_inspector).
     * usage: defined a rank of a detective.
     */
    private Rank rank;
    /**
     * armed: not null or blank.
     * usage: does a detective have a gun or something like that?
     */
    private boolean armed;
    /**
     * status: not null or blank.
     * usage: A detective has a status from EmploymentStatus enum (active, suspended, vacation, under_investigation, retired).
     */
    private EmploymentStatus status;
    /**
     * criminalCases: not null or blank.
     * Relationship: A Detective can join one or many CriminalCase (s). A CriminalCase can also have one or many detectives. [1 - *].
     * usage: contains criminal cases that a detective joined.
     */
    private Set<CriminalCase> criminalCaseSet;
    /**
     * trackEntries: not null or blank.
     * Relationship: A Detective can submit his evidence(s) in the system through a TrackEntry set. One evidence can only be submitted by a Detective. [1 - *]
     * usage: contains a track entry set.
     */
    private Set<TrackEntry> trackEntrySet;

    public Detective(long id, int version, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        super(id, version, createdAt, modifiedAt);
    }

    public Detective(long id, int version, LocalDateTime createdAt, LocalDateTime modifiedAt, String username, String firstName, String lastName, String password, LocalDateTime hiringDate, String badgeNumber, Rank rank, boolean armed, EmploymentStatus status) {
        super(id, version, createdAt, modifiedAt);
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.hiringDate = hiringDate;
        this.badgeNumber = badgeNumber;
        this.rank = rank;
        this.armed = armed;
        this.status = status;
    }

    @Override
    public int compareTo(Detective o) {
        return 0;
    }

    public void replaceWith(Detective newDetective) {
        super.setId(newDetective.getId());
        super.setVersion(newDetective.getVersion());
        super.setCreatedAt(newDetective.getCreatedAt());
        super.setModifiedAt(newDetective.getModifiedAt());

        this.username = newDetective.getUsername();
        this.firstName = newDetective.getFirstName();
        this.lastName = newDetective.getLastName();
        this.password = newDetective.getPassword();
        this.hiringDate = newDetective.getHiringDate();
        this.badgeNumber = newDetective.getBadgeNumber();
        this.rank = newDetective.getRank();
        this.armed = newDetective.isArmed();
        this.status = newDetective.getStatus();
        this.criminalCaseSet = newDetective.getCriminalCaseSet();
        this.trackEntrySet = newDetective.getTrackEntrySet();
    }
}
