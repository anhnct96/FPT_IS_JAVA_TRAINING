package model;

import lombok.*;
import model.enums.TrackAction;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Extends from AbstractEntity, having all instances from AbstractEntity.
 * Contains 5 common fields of a TrackEntry: date, evidence, detective, action, reason.
 * Every time a piece of evidence is submitted,
 * retrieved for analysis or returned, a track entry is created that contains the detective accessing the evidence
 * and the reason for the detective to do so
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TrackEntry extends AbstractEntity<TrackEntry>{
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


    @Override
    public int compareTo(TrackEntry o) {
        return 0;
    }

    public void replaceWith(TrackEntry newTrackEntry) {
        super.setId(newTrackEntry.getId());
        super.setVersion(newTrackEntry.getVersion());
        super.setCreatedAt(newTrackEntry.getCreatedAt());
        super.setModifiedAt(newTrackEntry.getModifiedAt());

        this.date = newTrackEntry.getDate();
        this.action = newTrackEntry.getAction();
        this.reason = newTrackEntry.getReason();

        this.detective = newTrackEntry.getDetective();
        this.evidence = newTrackEntry.getEvidence();
    }

    public TrackEntry(long id, int version, LocalDateTime createdAt, LocalDateTime modifiedAt, LocalDateTime date, Evidence evidence, Detective detective, TrackAction action, String reason) {
        super(id, version, createdAt, modifiedAt);
        this.date = date;
        this.evidence = evidence;
        this.detective = detective;
        this.action = action;
        this.reason = reason;
    }
}
