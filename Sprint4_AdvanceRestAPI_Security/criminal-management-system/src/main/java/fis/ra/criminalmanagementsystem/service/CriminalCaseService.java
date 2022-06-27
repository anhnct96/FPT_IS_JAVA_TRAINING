package fis.ra.criminalmanagementsystem.service;

import fis.ra.criminalmanagementsystem.core.CaseStatus;
import fis.ra.criminalmanagementsystem.dto.CriminalCaseDTO;

import java.util.Set;

public interface CriminalCaseService {
    CriminalCaseDTO saveCriminalCase(CriminalCaseDTO criminalCaseDTO);

    Set<CriminalCaseDTO> findAllCriminalCase();

    CriminalCaseDTO updateCriminalCase(CriminalCaseDTO criminalCaseDTO);

    void deleteCriminalCaseById(Long id);

    void deleteCriminalCase(CriminalCaseDTO criminalCaseDTO);

    CriminalCaseDTO findCriminalCaseById(Long id);

    CriminalCaseDTO findCriminalCase(CriminalCaseDTO criminalCaseDTO);

    Set<CriminalCaseDTO> findByStatus (CaseStatus status);

    Set<CriminalCaseDTO> findByDetectiveUsername (String username);
}
