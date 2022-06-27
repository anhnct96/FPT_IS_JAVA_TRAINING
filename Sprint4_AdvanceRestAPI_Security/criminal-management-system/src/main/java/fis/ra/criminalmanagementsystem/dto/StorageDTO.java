package fis.ra.criminalmanagementsystem.dto;

import fis.ra.criminalmanagementsystem.model.Evidence;
import fis.ra.criminalmanagementsystem.model.Storage;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
public class StorageDTO {
    private Long id;

    @NotEmpty(message = "No storage name")
    private String name;

    private String location;

    private List<Long> evidences;

    public static class Mapper {
        public static StorageDTO fromEntity(Storage storage) {
            return StorageDTO.builder()
                    .id(storage.getId())
                    .name(storage.getName())
                    .location(storage.getLocation())
                    .evidences(storage.getEvidenceSet()
                            .stream()
                            .map(Evidence::getId)
                            .collect(Collectors.toList()))
                    .build();
        }

        public static Storage fromDTO(StorageDTO storageDTO) {
            return Storage.builder()
                    .id(storageDTO.getId())
                    .name(storageDTO.getName())
                    .location(storageDTO.getLocation())
                    .build();
        }
    }
}
