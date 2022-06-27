package fis.ra.criminalmanagementsystem.service.impl;

import fis.ra.criminalmanagementsystem.controller.globalexceptionhandle.duplicatedexception.DuplicatedCriminalCaseNumberException;
import fis.ra.criminalmanagementsystem.controller.globalexceptionhandle.notfoundexception.CriminalCaseNotFoundException;
import fis.ra.criminalmanagementsystem.controller.globalexceptionhandle.notfoundexception.DetectiveNotFoundException;
import fis.ra.criminalmanagementsystem.core.CaseStatus;
import fis.ra.criminalmanagementsystem.dto.CriminalCaseDTO;
import fis.ra.criminalmanagementsystem.model.CriminalCase;
import fis.ra.criminalmanagementsystem.model.Detective;
import fis.ra.criminalmanagementsystem.model.Evidence;
import fis.ra.criminalmanagementsystem.repository.CriminalCaseRepo;
import fis.ra.criminalmanagementsystem.repository.DetectiveRepo;
import fis.ra.criminalmanagementsystem.repository.EvidenceRepo;
import fis.ra.criminalmanagementsystem.service.CriminalCaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CriminalCaseServiceImpl implements CriminalCaseService {

    CriminalCaseRepo criminalCaseRepo;
    DetectiveRepo detectiveRepo;
    EvidenceRepo evidenceRepo;

    public CriminalCaseServiceImpl(CriminalCaseRepo criminalCaseRepo, DetectiveRepo detectiveRepo, EvidenceRepo evidenceRepo) {
        this.criminalCaseRepo = criminalCaseRepo;
        this.detectiveRepo = detectiveRepo;
        this.evidenceRepo = evidenceRepo;
    }

    @Override
    @Transactional
    public CriminalCaseDTO saveCriminalCase(CriminalCaseDTO criminalCaseDTO) {
        if (criminalCaseRepo.findByNumber(criminalCaseDTO.getNumber()).isPresent()) {
            throw new DuplicatedCriminalCaseNumberException(criminalCaseDTO.getNumber());
        }

        Long leadInvestigatorId = criminalCaseDTO.getLeadInvestigator();
        Optional<Detective> leadDetective = detectiveRepo.findById(leadInvestigatorId);
        if (leadDetective.isEmpty()) {
            throw new DetectiveNotFoundException(leadInvestigatorId);
        }

        CriminalCase criminalCase = CriminalCaseDTO.Mapper.fromDTO(criminalCaseDTO);
        criminalCase.setCreatedAt(LocalDateTime.now());
        criminalCase.setModifiedAt(LocalDateTime.now());
        criminalCase.setLeadInvestigator(leadDetective.get());
        criminalCaseRepo.save(criminalCase);
        return criminalCaseDTO;
    }

    @Override
    public Set<CriminalCaseDTO> findAllCriminalCase() {
        return this.criminalCaseRepo
                .findAll()
                .stream()
                .map(CriminalCaseDTO.Mapper::fromEntity)
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public CriminalCaseDTO updateCriminalCase(CriminalCaseDTO criminalCaseDTO) {
        Optional<CriminalCase> optionalCriminalCase = criminalCaseRepo.findById(criminalCaseDTO.getId());

        if (optionalCriminalCase.isEmpty()) {
            throw new CriminalCaseNotFoundException(criminalCaseDTO.getId());
        }

        if (criminalCaseRepo.findByNumber(criminalCaseDTO.getNumber()).isPresent()) {
            throw new DuplicatedCriminalCaseNumberException(criminalCaseDTO.getNumber());
        }

        Long leadInvestigatorId = criminalCaseDTO.getLeadInvestigator();
        Optional<Detective> leadDetective = detectiveRepo.findById(leadInvestigatorId);
        if (leadDetective.isEmpty()) {
            throw new DetectiveNotFoundException(leadInvestigatorId);
        }

        Set<Detective> detectives = null;
        if (criminalCaseDTO.getAssignedDetective() != null) {
            detectives = criminalCaseDTO.getAssignedDetective().stream()
                    .map(detectiveRepo::findById)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
        }

        Set<Evidence> evidenceSet = null;
        if (criminalCaseDTO.getEvidence() != null) {
            evidenceSet = criminalCaseDTO.getEvidence().stream()
                    .map(evidenceRepo::findById)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
        }
        CriminalCase newCriminalCase = optionalCriminalCase.get();

        newCriminalCase.setModifiedAt(LocalDateTime.now());
        newCriminalCase.setNumber(criminalCaseDTO.getNumber());
        newCriminalCase.setType(criminalCaseDTO.getType());
        newCriminalCase.setShortDescription(criminalCaseDTO.getShortDescription());
        newCriminalCase.setDetailedDescription(criminalCaseDTO.getDetailedDescription());
        newCriminalCase.setStatus(criminalCaseDTO.getStatus());
        newCriminalCase.setNotes(criminalCaseDTO.getNotes());
        newCriminalCase.setLeadInvestigator(leadDetective.get());
        newCriminalCase.setAssigned(detectives);
        newCriminalCase.setEvidenceSet(evidenceSet);

        criminalCaseRepo.save(newCriminalCase);

        return criminalCaseDTO;
    }

    @Override
    @Transactional
    public void deleteCriminalCaseById(Long id) {
        if (criminalCaseRepo.findById(id).isEmpty()) {
            throw new CriminalCaseNotFoundException(id);
        }
        criminalCaseRepo.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteCriminalCase(CriminalCaseDTO criminalCaseDTO) {
        if (criminalCaseRepo.findById(criminalCaseDTO.getId()).isEmpty()) {
            throw new CriminalCaseNotFoundException(criminalCaseDTO.getId());
        }
        criminalCaseRepo.delete(CriminalCaseDTO.Mapper.fromDTO(criminalCaseDTO));
    }

    @Override
    public CriminalCaseDTO findCriminalCaseById(Long id) {
        Optional<CriminalCase> criminalCaseOptional = criminalCaseRepo.findById(id);
        if (criminalCaseOptional.isEmpty()) {
            throw new CriminalCaseNotFoundException(id);
        }
        return CriminalCaseDTO.Mapper.fromEntity(criminalCaseOptional.get());
    }

    @Override
    public CriminalCaseDTO findCriminalCase(CriminalCaseDTO criminalCaseDTO) {
        Optional<CriminalCase> criminalCaseOptional = criminalCaseRepo.findById(criminalCaseDTO.getId());
        if (criminalCaseOptional.isEmpty()) {
            throw new CriminalCaseNotFoundException(criminalCaseDTO.getId());
        }
        return CriminalCaseDTO.Mapper.fromEntity(criminalCaseOptional.get());
    }

    @Override
    public Set<CriminalCaseDTO> findByStatus(CaseStatus status) {
        return criminalCaseRepo.findByStatus(status)
                .stream()
                .map(CriminalCaseDTO.Mapper::fromEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<CriminalCaseDTO> findByDetectiveUsername(String username) {
        if (detectiveRepo.findByUsername(username).isEmpty()) {
            throw new DetectiveNotFoundException(username);
        }
        return criminalCaseRepo.findByAssigned_Username(username)
                .stream()
                .map(CriminalCaseDTO.Mapper::fromEntity)
                .collect(Collectors.toSet());
    }
}
