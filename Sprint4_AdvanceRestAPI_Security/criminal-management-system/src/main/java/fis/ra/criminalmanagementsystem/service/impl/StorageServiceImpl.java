package fis.ra.criminalmanagementsystem.service.impl;

import fis.ra.criminalmanagementsystem.controller.globalexceptionhandle.duplicatedexception.DuplicatedStorageNameException;
import fis.ra.criminalmanagementsystem.controller.globalexceptionhandle.notfoundexception.StorageNotFoundException;
import fis.ra.criminalmanagementsystem.dto.StorageDTO;
import fis.ra.criminalmanagementsystem.model.Evidence;
import fis.ra.criminalmanagementsystem.model.Storage;
import fis.ra.criminalmanagementsystem.repository.EvidenceRepo;
import fis.ra.criminalmanagementsystem.repository.StorageRepo;
import fis.ra.criminalmanagementsystem.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    StorageRepo storageRepo;
    EvidenceRepo evidenceRepo;

    public StorageServiceImpl(StorageRepo storageRepo, EvidenceRepo evidenceRepo) {
        this.storageRepo = storageRepo;
        this.evidenceRepo = evidenceRepo;
    }

    @Override
    @Transactional
    public StorageDTO saveStorage(StorageDTO storageDTO) {
        if (storageRepo.findByName(storageDTO.getName()).isPresent()) {
            throw new DuplicatedStorageNameException(storageDTO.getName());
        }
        Storage storage = StorageDTO.Mapper.fromDTO(storageDTO);
        storage.setCreatedAt(LocalDateTime.now());
        storage.setModifiedAt(LocalDateTime.now());
        storageRepo.save(storage);
        return storageDTO;
    }

    @Override
    public Set<StorageDTO> findAllStorage() {
        return this.storageRepo
                .findAll()
                .stream()
                .map(StorageDTO.Mapper::fromEntity)
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public StorageDTO updateStorage(StorageDTO storageDTO) {
        Optional<Storage> optionalStorage = storageRepo.findById(storageDTO.getId());
        if (optionalStorage.isEmpty()) {
            throw new StorageNotFoundException(storageDTO.getId());
        }

        if (storageRepo.findByName(storageDTO.getName()).isPresent()) {
            throw new DuplicatedStorageNameException(storageDTO.getName());
        }

        Set<Evidence> evidences = null;
        if (storageDTO.getEvidences() != null) {
            evidences = storageDTO.getEvidences().stream()
                    .map(evidenceRepo::findById)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
        }

        Storage newStorage = optionalStorage.get();
        newStorage.setModifiedAt(LocalDateTime.now());
        newStorage.setName(storageDTO.getName());
        newStorage.setLocation(storageDTO.getLocation());
        newStorage.setEvidenceSet(evidences);

        storageRepo.save(newStorage);

        return storageDTO;
    }

    @Override
    @Transactional
    public void deleteStorageById(Long id) {
        if (storageRepo.findById(id).isEmpty()) {
            throw new StorageNotFoundException(id);
        }
        storageRepo.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteStorage(StorageDTO storageDTO) {
        if (storageRepo.findById(storageDTO.getId()).isEmpty()) {
            throw new StorageNotFoundException(storageDTO.getId());
        }
        storageRepo.delete(StorageDTO.Mapper.fromDTO(storageDTO));
    }

    @Override
    public StorageDTO findStorageById(Long id) {
        Optional<Storage> storageOptional = storageRepo.findById(id);
        if (storageOptional.isEmpty()) {
            throw new StorageNotFoundException(id);
        }
        return StorageDTO.Mapper.fromEntity(storageOptional.get());
    }

    @Override
    public StorageDTO findStorage(StorageDTO storageDTO) {
        Optional<Storage> storageOptional = storageRepo.findById(storageDTO.getId());
        if (storageOptional.isEmpty()) {
            throw new StorageNotFoundException(storageDTO.getId());
        }
        return StorageDTO.Mapper.fromEntity(storageOptional.get());
    }
}
