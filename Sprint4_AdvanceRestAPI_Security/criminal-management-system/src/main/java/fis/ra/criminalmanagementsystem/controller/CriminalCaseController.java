package fis.ra.criminalmanagementsystem.controller;

import fis.ra.criminalmanagementsystem.core.CaseStatus;
import fis.ra.criminalmanagementsystem.dto.CriminalCaseDTO;
import fis.ra.criminalmanagementsystem.service.CriminalCaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/case")
@CrossOrigin("*")
public class CriminalCaseController {

    private CriminalCaseService criminalCaseService;

    public CriminalCaseController(CriminalCaseService criminalCaseService) {
        this.criminalCaseService = criminalCaseService;
    }

    @PostMapping("/create")
    public ResponseEntity saveCriminalCase(@RequestBody CriminalCaseDTO criminalCaseDTO) {
        return ResponseEntity.ok(this.criminalCaseService.saveCriminalCase(criminalCaseDTO));
    }

    @GetMapping("/read")
    public Set<CriminalCaseDTO> findAllCriminalCase() {
        return this.criminalCaseService.findAllCriminalCase();
    }

    @PutMapping(value = "/update")
    public ResponseEntity updateCriminalCase(@RequestBody CriminalCaseDTO criminalCaseDTO) {
        return ResponseEntity.ok(criminalCaseService.updateCriminalCase(criminalCaseDTO));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCriminalCaseById(@PathVariable Long id) {
        criminalCaseService.deleteCriminalCaseById(id);
    }

    @GetMapping("/find/{id}")
    public CriminalCaseDTO findCriminalCaseById(@PathVariable Long id) {
        return criminalCaseService.findCriminalCaseById(id);
    }

    @GetMapping("/find/status={status}")
    public Set<CriminalCaseDTO> findCriminalCaseByStatus(@PathVariable CaseStatus status) {
        return criminalCaseService.findByStatus(status);
    }

    @GetMapping("/find/username={username}")
    public Set<CriminalCaseDTO> findCriminalCaseByDetectiveUsername(@PathVariable String username) {
        return criminalCaseService.findByDetectiveUsername(username);
    }

}
