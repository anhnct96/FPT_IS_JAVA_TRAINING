package model;

import model.enumerable.TrackAction;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Extends from AbstractEntity, having all instances from AbstractEntity.
 * Contains 5 common fields of a TrackEntry: date, evidence, detective, action, reason.
 * Every time a piece of evidence is submitted,
 * retrieved for analysis or returned, a track entry is created that contains the detective accessing the evidence
 * and the reason for the detective to do so
 */
public class TrackEntry extends AbstractEntity{
    /**
     * date: not null or blank.
     * usage: contains the tracking date.
     */
    protected LocalDateTime date;
    /**
     * evidence: not null or blank.
     * Relationship: tracking one evidence each track entry. One evidence can be tracked one or many times. [1 - 1]
     * usage: defined the tracked evidence
     */
    private Evidence evidence;
    /**
     * detective: not null or blank
     * Relationship: one detective can track entry one or many times. One track entry is tracked by only one detective. [1 - 1]
     * usage: defined the tracked detective
     */
    private Detective detective;
    /**
     * action: not null or blank.
     * A TrackEntry action is classified in one of the following categories: (submitted, retrieved, returned).
     * usage: defined the action of the tracking entry
     */
    private TrackAction action;
    /**
     * reason: not null or blank
     * requirement: Following the Standard string rule in the AbstractEntity class. (Contains {5-50} characters)
     * usage: defined the reason why a detective does this track
     */
    private String reason;

    public TrackEntry(long id, int version, LocalDateTime createdAt, LocalDateTime modifiedAt, LocalDateTime date, Evidence evidence, Detective detective, TrackAction action, String reason) {
        super(id, version, createdAt, modifiedAt);
        this.date = date;
        this.evidence = evidence;
        this.detective = detective;
        this.action = action;
        this.reason = reason;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Evidence getEvidence() {
        return evidence;
    }

    public void setEvidence(Evidence evidence) {
        this.evidence = evidence;
    }

    public Detective getDetective() {
        return detective;
    }

    public void setDetective(Detective detective) {
        this.detective = detective;
    }

    public TrackAction getAction() {
        return action;
    }

    public void setAction(TrackAction action) {
        this.action = action;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "TrackEntry{" +
                "date=" + date +
                ", evidence=" + evidence +
                ", detective=" + detective +
                ", action=" + action +
                ", reason='" + reason + '\'' +
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
        TrackEntry that = (TrackEntry) o;
        return date.equals(that.date) && evidence.equals(that.evidence) && detective.equals(that.detective) && action == that.action && reason.equals(that.reason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), date, evidence, detective, action, reason);
    }
}
