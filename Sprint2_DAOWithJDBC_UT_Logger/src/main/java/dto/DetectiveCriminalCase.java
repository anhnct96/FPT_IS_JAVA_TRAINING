package dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class DetectiveCriminalCase {
    private long criminalCaseId;
    private long detectiveId;

}
