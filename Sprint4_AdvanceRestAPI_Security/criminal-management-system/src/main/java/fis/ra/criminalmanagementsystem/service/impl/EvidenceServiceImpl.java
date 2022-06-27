package fis.ra.criminalmanagementsystem.service.impl;

import fis.ra.criminalmanagementsystem.controller.globalexceptionhandle.duplicatedexception.DuplicatedEvidenceNumberException;
import fis.ra.criminalmanagementsystem.controller.globalexceptionhandle.notfoundexception.CriminalCaseNotFoundException;
import fis.ra.criminalmanagementsystem.controller.globalexceptionhandle.notfoundexception.EvidenceNotFoundException;
import fis.ra.criminalmanagementsystem.controller.globalexceptionhandle.notfoundexception.StorageNotFoundException;
import fis.ra.criminalmanagementsystem.dto.EvidenceDTO;
import fis.ra.criminalmanagementsystem.model.CriminalCase;
import fis.ra.criminalmanagementsystem.model.Evidence;
import fis.ra.criminalmanagementsystem.model.Storage;
import fis.ra.criminalmanagementsystem.repository.CriminalCaseRepo;
import fis.ra.criminalmanagementsystem.repository.EvidenceRepo;
import fis.ra.criminalmanagementsystem.repository.StorageRepo;
import fis.ra.criminalmanagementsystem.repository.TrackEntryRepo;
import fis.ra.criminalmanagementsystem.service.EvidenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EvidenceServiceImpl implements EvidenceService {

    EvidenceRepo evidenceRepo;
    TrackEntryRepo trackEntryRepo;
    StorageRepo storageRepo;
    CriminalCaseRepo criminalCaseRepo;

    public EvidenceServiceImpl(EvidenceRepo evidenceRepo, TrackEntryRepo trackEntryRepo, StorageRepo storageRepo, CriminalCaseRepo criminalCaseRepo) {
        this.evidenceRepo = evidenceRepo;
        this.trackEntryRepo = trackEntryRepo;
        this.storageRepo = storageRepo;
        this.criminalCaseRepo = criminalCaseRepo;
    }

    @Override
    @Transactional
    public EvidenceDTO saveEvidence(EvidenceDTO evidenceDTO) {
        if (evidenceRepo.findByNumber(evidenceDTO.getNumber()).isPresent()) {
            throw new DuplicatedEvidenceNumberException(evidenceDTO.getNumber());
        }

        Long criminalCaseId = evidenceDTO.getCriminalCase();
        Optional<CriminalCase> criminalCase = criminalCaseRepo.findById(criminalCaseId);
        if (criminalCase.isEmpty()) {
            throw new CriminalCaseNotFoundException(criminalCaseId);
        }

        Long storageId = evidenceDTO.getStorage();
        Optional<Storage> storage = storageRepo.findById(storageId);
        if (storage.isEmpty()) {
            throw new StorageNotFoundException(storageId);
        }

        Evidence evidence = EvidenceDTO.Mapper.fromDTO(evidenceDTO);
        evidence.setCreatedAt(LocalDateTime.now());
        evidence.setModifiedAt(LocalDateTime.now());
        evidence.setCriminalCase(criminalCase.get());
        evidence.setStorage(storage.get());
        evidenceRepo.save(evidence);
        return evidenceDTO;
    }

    @Override
    public Set<EvidenceDTO> findAllEvidence() {
        return this.evidenceRepo
                .findAll()
                .stream()
                .map(EvidenceDTO.Mapper::fromEntity)
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public EvidenceDTO updateEvidence(EvidenceDTO evidenceDTO) {
        Optional<Evidence> optionalEvidence = evidenceRepo.findById(evidenceDTO.getId());

        if (optionalEvidence.isEmpty()) {
            throw new EvidenceNotFoundException(evidenceDTO.getId());
        }

        if (evidenceRepo.findByNumber(evidenceDTO.getNumber()).isPresent()) {
            throw new DuplicatedEvidenceNumberException(evidenceDTO.getNumber());
        }

        Long criminalCaseId = evidenceDTO.getCriminalCase();
        Optional<CriminalCase> criminalCase = criminalCaseRepo.findById(criminalCaseId);
        if (criminalCase.isEmpty()) {
            throw new CriminalCaseNotFoundException(criminalCaseId);
        }

        Long storageId = evidenceDTO.getStorage();
        Optional<Storage> storage = storageRepo.findById(storageId);
        if (storage.isEmpty()) {
            throw new StorageNotFoundException(storageId);
        }

        Evidence newEvidence = optionalEvidence.get();

        newEvidence.setModifiedAt(LocalDateTime.now());
        newEvidence.setArchived(evidenceDTO.getArchived());
        newEvidence.setItemName(evidenceDTO.getItemName());
        newEvidence.setNotes(evidenceDTO.getNotes());
        newEvidence.setNumber(evidenceDTO.getNumber());
        newEvidence.setCriminalCase(criminalCase.get());
        newEvidence.setStorage(storage.get());

        evidenceRepo.save(newEvidence);

        return evidenceDTO;
    }

    @Override
    @Transactional
    public void deleteEvidenceById(Long id) {
        if (evidenceRepo.findById(id).isEmpty()) {
            throw new EvidenceNotFoundException(id);
        }
        evidenceRepo.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteEvidence(EvidenceDTO evidenceDTO) {
        if (evidenceRepo.findById(evidenceDTO.getId()).isEmpty()) {
            throw new EvidenceNotFoundException(evidenceDTO.getId());
        }
        evidenceRepo.delete(EvidenceDTO.Mapper.fromDTO(evidenceDTO));
    }

    @Override
    public EvidenceDTO findEvidenceById(Long id) {
        Optional<Evidence> evidenceOptional = evidenceRepo.findById(id);
        if (evidenceOptional.isEmpty()) {
            throw new EvidenceNotFoundException(id);
        }
        return EvidenceDTO.Mapper.fromEntity(evidenceOptional.get());
    }

    @Override
    public EvidenceDTO findEvidence(EvidenceDTO evidenceDTO) {
        Optional<Evidence> evidenceOptional = evidenceRepo.findById(evidenceDTO.getId());
        if (evidenceOptional.isEmpty()) {
            throw new EvidenceNotFoundException(evidenceDTO.getId());
        }
        return EvidenceDTO.Mapper.fromEntity(evidenceOptional.get());
    }

    @Override
    public Set<EvidenceDTO> findByCaseNumber(String caseNumber) {
        if (criminalCaseRepo.findByNumber(caseNumber).isEmpty()) {
            throw new CriminalCaseNotFoundException(caseNumber);
        }
        return evidenceRepo.findByCriminalCase_Number(caseNumber)
                .stream()
                .map(EvidenceDTO.Mapper::fromEntity)
                .collect(Collectors.toSet());
    }
}
