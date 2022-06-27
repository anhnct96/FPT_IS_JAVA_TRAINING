package fis.ra.criminalmanagementsystem.controller;

import fis.ra.criminalmanagementsystem.controller.globalexceptionhandle.LeadingCriminalCaseDetectiveException;
import fis.ra.criminalmanagementsystem.dto.DetectiveDTO;
import fis.ra.criminalmanagementsystem.service.DetectiveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/detective")
@CrossOrigin("*")
public class DetectiveController {

    private final DetectiveService DetectiveService;

    public DetectiveController(DetectiveService DetectiveService) {
        this.DetectiveService = DetectiveService;
    }

    @PostMapping("/create")
    public ResponseEntity saveDetective(@RequestBody DetectiveDTO detectiveDTO) {
        return ResponseEntity.ok(this.DetectiveService.saveDetective(detectiveDTO));
    }

    @GetMapping("/read")
    public Set<DetectiveDTO> findAllDetective() {
        return this.DetectiveService.findAllDetective();
    }

    @PutMapping(value = "/update")
    public ResponseEntity updateDetective(@RequestBody DetectiveDTO detectiveDTO) {
        return ResponseEntity.ok(DetectiveService.updateDetective(detectiveDTO));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDetectiveById(@PathVariable Long id) throws LeadingCriminalCaseDetectiveException {
        DetectiveService.deleteDetectiveById(id);
    }

    @GetMapping("/find/{id}")
    public DetectiveDTO findDetectiveById(@PathVariable Long id) {
        return DetectiveService.findDetectiveById(id);
    }
}
