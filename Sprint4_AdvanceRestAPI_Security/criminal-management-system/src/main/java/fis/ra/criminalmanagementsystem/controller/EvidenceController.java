package fis.ra.criminalmanagementsystem.controller;

import fis.ra.criminalmanagementsystem.dto.CriminalCaseDTO;
import fis.ra.criminalmanagementsystem.dto.EvidenceDTO;
import fis.ra.criminalmanagementsystem.model.Evidence;
import fis.ra.criminalmanagementsystem.service.CriminalCaseService;
import fis.ra.criminalmanagementsystem.service.EvidenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/evidence")
@CrossOrigin("*")
public class EvidenceController {

    private EvidenceService evidenceService;

    public EvidenceController(EvidenceService evidenceService) {
        this.evidenceService = evidenceService;
    }

    @PostMapping("/create")
    public ResponseEntity saveEvidence(@RequestBody EvidenceDTO evidenceDTO) {
        return ResponseEntity.ok(this.evidenceService.saveEvidence(evidenceDTO));
    }

    @GetMapping("/read")
    public Set<EvidenceDTO> findAllEvidence() {
        return this.evidenceService.findAllEvidence();
    }

    @PutMapping(value = "/update")
    public ResponseEntity updateEvidence(@RequestBody EvidenceDTO evidenceDTO) {
        return ResponseEntity.ok(evidenceService.updateEvidence(evidenceDTO));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEvidenceById(@PathVariable Long id) {
        evidenceService.deleteEvidenceById(id);
    }

    @GetMapping("/find/{id}")
    public EvidenceDTO findEvidenceById(@PathVariable Long id) {
        return evidenceService.findEvidenceById(id);
    }

    @GetMapping("/find/casenumber={caseNumber}")
    public Set<EvidenceDTO> findEvidenceByCaseNumber(@PathVariable String caseNumber) {
        return evidenceService.findByCaseNumber(caseNumber);
    }
}
