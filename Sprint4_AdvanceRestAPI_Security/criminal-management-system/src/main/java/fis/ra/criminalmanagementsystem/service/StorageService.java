package fis.ra.criminalmanagementsystem.service;

import fis.ra.criminalmanagementsystem.dto.StorageDTO;

import java.util.Set;

public interface StorageService {
    StorageDTO saveStorage(StorageDTO storageDTO);

    Set<StorageDTO> findAllStorage();

    StorageDTO updateStorage(StorageDTO storageDTO);

    void deleteStorageById(Long id);

    void deleteStorage(StorageDTO storageDTO);

    StorageDTO findStorageById(Long id);

    StorageDTO findStorage(StorageDTO storageDTO);
}
