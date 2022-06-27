package fis.ra.criminalmanagementsystem.dto;

import fis.ra.criminalmanagementsystem.model.Evidence;
import fis.ra.criminalmanagementsystem.model.TrackEntry;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
public class EvidenceDTO {

    private Long id;
    @NotEmpty(message = "No evidence number")
    private String number;
    private String itemName;
    private String notes;
    private Boolean archived;
    private List<Long> trackEntries;
    private Long criminalCase;
    private Long storage;

    public static class Mapper {
        public static EvidenceDTO fromEntity(Evidence evidence) {
            return EvidenceDTO.builder()
                    .id(evidence.getId())
                    .number(evidence.getNumber())
                    .itemName(evidence.getItemName())
                    .notes(evidence.getNotes())
                    .archived(evidence.getArchived())
                    .trackEntries(
                            evidence.getTrackEntries()
                                    .stream()
                                    .map(TrackEntry::getId)
                                    .collect(Collectors.toList())
                    )
                    .criminalCase(evidence.getCriminalCase().getId())
                    .storage(evidence.getStorage().getId())
                    .build();
        }

        public static Evidence fromDTO(EvidenceDTO evidenceDTO) {
            return Evidence.builder()
                    .id(evidenceDTO.getId())
                    .number(evidenceDTO.getNumber())
                    .itemName(evidenceDTO.getItemName())
                    .notes(evidenceDTO.getNotes())
                    .archived(evidenceDTO.getArchived())
                    .build();
        }
    }
}
