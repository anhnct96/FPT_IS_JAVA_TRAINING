package fis.ra.criminalmanagementsystem.controller;

import fis.ra.criminalmanagementsystem.dto.StorageDTO;
import fis.ra.criminalmanagementsystem.service.StorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/storage")
@CrossOrigin("*")
public class StorageController {
    private final StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/create")
    public ResponseEntity saveStorage(@RequestBody StorageDTO storageDTO) {
        return ResponseEntity.ok(this.storageService.saveStorage(storageDTO));
    }

    @GetMapping("/read")
    public Set<StorageDTO> findAllStorage() {
        return this.storageService.findAllStorage();
    }

    @PutMapping(value = "/update")
    public ResponseEntity updateStorage(@RequestBody StorageDTO storageDTO) {
        return ResponseEntity.ok(storageService.updateStorage(storageDTO));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStorageById(@PathVariable Long id) {
        storageService.deleteStorageById(id);
    }

    @GetMapping("/find/{id}")
    public StorageDTO findStorageById(@PathVariable Long id) {
        return storageService.findStorageById(id);
    }
}
