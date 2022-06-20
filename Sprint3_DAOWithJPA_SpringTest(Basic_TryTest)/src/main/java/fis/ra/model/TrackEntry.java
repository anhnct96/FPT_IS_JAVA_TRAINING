package fis.ra.model;

import fis.ra.core.TrackAction;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "track_entry")
@AttributeOverride(name = "id", column = @Column(name = "track_entry_id"))
public class TrackEntry extends AbstractEntity{
    @Column(name = "date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    protected LocalDateTime date;
    @Column(name = "action", length = 30, columnDefinition = "varchar(30) default 'RETRIEVED'")
    @Enumerated(EnumType.STRING)
    private TrackAction action;
    @Column(name = "reason", columnDefinition = "varchar(255) default ''")
    private String reason;

    @OneToOne
    @JoinColumn(name = "evidence_id")
    private Evidence evidence;

    @ManyToOne
    @JoinColumn(name = "detective_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Detective detective;
}