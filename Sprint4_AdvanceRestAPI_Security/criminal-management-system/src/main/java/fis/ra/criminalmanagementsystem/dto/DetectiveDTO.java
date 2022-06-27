package fis.ra.criminalmanagementsystem.dto;

import fis.ra.criminalmanagementsystem.core.EmploymentStatus;
import fis.ra.criminalmanagementsystem.core.Rank;
import fis.ra.criminalmanagementsystem.model.CriminalCase;
import fis.ra.criminalmanagementsystem.model.Detective;
import fis.ra.criminalmanagementsystem.model.TrackEntry;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
public class DetectiveDTO {
    private Long id;

    @NotEmpty(message = "No username")
    private String username;

    @NotEmpty
    @Min(value = 8, message = "Password need 8 or more characters")
    private String password;

    private String firstName;

    private String lastName;

    private LocalDateTime hiringDate;

    private String badgeNumber;

    private Rank rank;

    private boolean armed;

    private EmploymentStatus status;

    private List<Long> criminalCases;

    private List<Long> trackEntries;

    public static class Mapper {
        public static DetectiveDTO fromEntity(Detective detective) {
            return DetectiveDTO.builder()
                    .id(detective.getId())
                    .username(detective.getUsername())
                    .password(detective.getPassword())
                    .firstName(detective.getFirstName())
                    .lastName(detective.getLastName())
                    .hiringDate(detective.getHiringDate())
                    .badgeNumber(detective.getBadgeNumber())
                    .rank(detective.getRank())
                    .armed(detective.isArmed())
                    .status(detective.getStatus())
                    .criminalCases(detective.getCriminalCases()
                            .stream()
                            .map(CriminalCase::getId)
                            .collect(Collectors.toList()))
                    .trackEntries(detective.getTrackEntries()
                            .stream()
                            .map(TrackEntry::getId)
                            .collect(Collectors.toList()))
                    .build();
        }

        public static Detective fromDTO(DetectiveDTO detectiveDTO) {
            return Detective.builder()
                    .id(detectiveDTO.getId())
                    .username(detectiveDTO.getUsername())
                    .password(detectiveDTO.getPassword())
                    .firstName(detectiveDTO.getFirstName())
                    .lastName(detectiveDTO.getLastName())
                    .hiringDate(detectiveDTO.getHiringDate())
                    .badgeNumber(detectiveDTO.getBadgeNumber())
                    .rank(detectiveDTO.getRank())
                    .armed(detectiveDTO.isArmed())
                    .status(detectiveDTO.getStatus())
                    .build();
        }
    }
}
