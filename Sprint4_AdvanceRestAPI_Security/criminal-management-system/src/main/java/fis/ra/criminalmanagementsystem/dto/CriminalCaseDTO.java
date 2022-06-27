package fis.ra.criminalmanagementsystem.dto;

import fis.ra.criminalmanagementsystem.core.CaseStatus;
import fis.ra.criminalmanagementsystem.core.CaseType;
import fis.ra.criminalmanagementsystem.model.CriminalCase;
import fis.ra.criminalmanagementsystem.model.Detective;
import fis.ra.criminalmanagementsystem.model.Evidence;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
public class CriminalCaseDTO {
    private Long id;

    @NotEmpty(message = "No case number")
    private String number;

    private CaseType type;

    private String shortDescription;

    private String detailedDescription;

    private CaseStatus status;

    private String notes;

    private List<Long> evidence;

    private Long leadInvestigator;

    private List<Long> assignedDetective;

    public static class Mapper {

        public static CriminalCaseDTO fromEntity(CriminalCase criminalCase) {
            return CriminalCaseDTO.builder()
                    .id(criminalCase.getId())
                    .number(criminalCase.getNumber())
                    .type(criminalCase.getType())
                    .shortDescription(criminalCase.getShortDescription())
                    .detailedDescription(criminalCase.getDetailedDescription())
                    .status(criminalCase.getStatus())
                    .notes(criminalCase.getNotes())
                    .evidence(criminalCase.getEvidenceSet()
                            .stream()
                            .map(Evidence::getId)
                            .collect(Collectors.toList()))
                    .leadInvestigator(criminalCase.getLeadInvestigator().getId())
                    .assignedDetective(criminalCase.getAssigned()
                            .stream()
                            .map(Detective::getId)
                            .collect(Collectors.toList()))
                    .build();
        }

        public static CriminalCase fromDTO(CriminalCaseDTO criminalCaseDTO) {
            return CriminalCase.builder()
                    .id(criminalCaseDTO.getId())
                    .number(criminalCaseDTO.getNumber())
                    .type(criminalCaseDTO.getType())
                    .shortDescription(criminalCaseDTO.getShortDescription())
                    .detailedDescription(criminalCaseDTO.getDetailedDescription())
                    .status(criminalCaseDTO.getStatus())
                    .notes(criminalCaseDTO.getNotes())
                    .build();
        }
    }
}
