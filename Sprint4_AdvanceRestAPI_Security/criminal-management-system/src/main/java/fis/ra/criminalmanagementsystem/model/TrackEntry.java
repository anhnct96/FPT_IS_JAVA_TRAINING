package fis.ra.criminalmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import fis.ra.criminalmanagementsystem.core.TrackAction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "track_entry")
@AttributeOverride(name = "id", column = @Column(name = "track_entry_id"))
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class TrackEntry extends AbstractEntity {
    @Column(name = "date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    protected LocalDateTime date;
    @Column(name = "action", length = 30, columnDefinition = "varchar(30) default 'UNCATEGORIZED'")
    @Enumerated(EnumType.STRING)
    private TrackAction action;
    @Column(name = "reason", columnDefinition = "varchar(255) default ''")
    private String reason;

    @OneToOne
    @JoinColumn(name = "evidence_id", foreignKey = @ForeignKey(name = "evidence_fk"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Evidence evidence;

    @ManyToOne
    @JoinColumn(name = "detective_id", foreignKey = @ForeignKey(name = "detective_fk"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Detective detective;

    public TrackEntry() {
        super();
        this.date = LocalDateTime.now();
        this.action = TrackAction.UNCATEGORIZED;
        this.reason = "";
    }

    @Override
    public String toString() {
        return "TrackEntry{" +
                "date=" + date +
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
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }
}
