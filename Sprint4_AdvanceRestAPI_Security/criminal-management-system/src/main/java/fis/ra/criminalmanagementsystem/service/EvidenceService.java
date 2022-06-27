package fis.ra.criminalmanagementsystem.service;

import fis.ra.criminalmanagementsystem.dto.EvidenceDTO;

import java.util.Set;

public interface EvidenceService {
    EvidenceDTO saveEvidence(EvidenceDTO evidenceDTO);

    Set<EvidenceDTO> findAllEvidence();

    EvidenceDTO updateEvidence(EvidenceDTO evidenceDTO);

    void deleteEvidenceById(Long id);

    void deleteEvidence(EvidenceDTO evidenceDTO);

    EvidenceDTO findEvidenceById(Long id);

    EvidenceDTO findEvidence(EvidenceDTO evidenceDTO);

    Set<EvidenceDTO> findByCaseNumber (String caseNumber);
}
