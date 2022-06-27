package fis.ra.criminalmanagementsystem.dto;

import fis.ra.criminalmanagementsystem.core.TrackAction;
import fis.ra.criminalmanagementsystem.model.TrackEntry;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class TrackEntryDTO {
    private Long id;
    private LocalDateTime date;
    private TrackAction action;
    private String reason;
    private Long detective;
    private Long evidence;

    public static class Mapper {
        public static TrackEntryDTO fromEntity(TrackEntry trackEntry) {
            return TrackEntryDTO.builder()
                    .id(trackEntry.getId())
                    .date(trackEntry.getDate())
                    .action(trackEntry.getAction())
                    .reason(trackEntry.getReason())
                    .detective(trackEntry.getDetective().getId())
                    .evidence(trackEntry.getEvidence().getId())
                    .build();
        }

        public static TrackEntry fromDTO(TrackEntryDTO trackEntryDTO) {
            return TrackEntry.builder()
                    .id(trackEntryDTO.getId())
                    .date(trackEntryDTO.getDate())
                    .action(trackEntryDTO.getAction())
                    .reason(trackEntryDTO.getReason())
                    .build();
        }
    }
}
