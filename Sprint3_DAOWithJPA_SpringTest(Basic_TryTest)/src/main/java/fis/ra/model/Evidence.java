package fis.ra.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "evidence")
@AttributeOverride(name = "id", column = @Column(name = "evidence_id"))
public class Evidence extends AbstractEntity {

    @Column(name = "evidence_number", unique = true, columnDefinition = "varchar(255) default ''")
    private String number;
    @Column(name = "item_name", columnDefinition = "varchar(255) default ''")
    private String itemName;
    //very big text
    @Column(name = "notes")
    @Lob
    private String notes;
    @Column(name = "archived", columnDefinition = "TINYINT", length = 1)
    private Boolean archived = false;
    @OneToMany(mappedBy = "evidence")
    private Set<TrackEntry> trackEntries = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "criminal_case_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TrackEnTtry criminalCase;

    @ManyToOne
    @JoinColumn(name = "storage_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Storage storage;

    public boolean addTrackEntry(TrackEntry trackEntry){
        trackEntry.setEvidence(this);
        return trackEntries.add(trackEntry);
    }
}
