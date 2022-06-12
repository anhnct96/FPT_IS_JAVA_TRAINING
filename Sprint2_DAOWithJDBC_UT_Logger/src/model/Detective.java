package model;

import model.enumerable.EmploymentStatus;
import model.enumerable.Rank;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

/**
 * Extends from AbstractEntity, thus, a detective has all instances from AbstractEntity.
 * Contains 7 common fields of a Detective: Person, BadgeNumber, Rank, Armed, EmploymentStatus, Set<CriminalCase>, Set<TrackEntry>.
 */
public class Detective extends AbstractEntity{
    /**
     * Person: not null or blank, need to meet all requirements of a person.
     * Relationship: A Detective is a Person, a Person may not be a Detective. [1 - 0..1].
     * usage: defined a detective is a person.
     */
    private Person person;
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
    private Set<CriminalCase> criminalCases;
    /**
     * trackEntries: not null or blank.
     * Relationship: A Detective can submit his evidence(s) in the system through a TrackEntry set. One evidence can only be submitted by a Detective. [1 - *]
     * usage: contains a track entry set.
     */
    private Set<TrackEntry> trackEntries;

    /**
     * Constructor with full instances.
     * @param id
     * @param version
     * @param createdAt
     * @param modifiedAt
     * @param person
     * @param badgeNumber
     * @param rank
     * @param armed
     * @param status
     * @param criminalCases
     * @param trackEntries
     */
    public Detective(long id, int version, LocalDateTime createdAt, LocalDateTime modifiedAt, Person person, String badgeNumber, Rank rank, boolean armed, EmploymentStatus status, Set<CriminalCase> criminalCases, Set<TrackEntry> trackEntries) {
        super(id, version, createdAt, modifiedAt);
        this.person = person;
        this.badgeNumber = badgeNumber;
        this.rank = rank;
        this.armed = armed;
        this.status = status;
        this.criminalCases = criminalCases;
        this.trackEntries = trackEntries;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getBadgeNumber() {
        return badgeNumber;
    }

    public void setBadgeNumber(String badgeNumber) {
        this.badgeNumber = badgeNumber;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public boolean isArmed() {
        return armed;
    }

    public void setArmed(boolean armed) {
        this.armed = armed;
    }

    public EmploymentStatus getStatus() {
        return status;
    }

    public void setStatus(EmploymentStatus status) {
        this.status = status;
    }

    public Set<CriminalCase> getCriminalCases() {
        return criminalCases;
    }

    public void setCriminalCases(Set<CriminalCase> criminalCases) {
        this.criminalCases = criminalCases;
    }

    public Set<TrackEntry> getTrackEntries() {
        return trackEntries;
    }

    public void setTrackEntries(Set<TrackEntry> trackEntries) {
        this.trackEntries = trackEntries;
    }

    @Override
    public String toString() {
        return "DetectiveCase{" +
                "person=" + person +
                ", badgeNumber='" + badgeNumber + '\'' +
                ", rank=" + rank +
                ", armed=" + armed +
                ", status=" + status +
                ", criminalCases=" + criminalCases +
                ", trackEntries=" + trackEntries +
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
        Detective that = (Detective) o;
        return armed == that.armed && person.equals(that.person) && badgeNumber.equals(that.badgeNumber) && rank == that.rank && status == that.status && criminalCases.equals(that.criminalCases) && trackEntries.equals(that.trackEntries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), person, badgeNumber, rank, armed, status, criminalCases, trackEntries);
    }
}
