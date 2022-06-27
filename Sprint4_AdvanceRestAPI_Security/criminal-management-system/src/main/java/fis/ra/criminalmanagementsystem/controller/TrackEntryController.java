package fis.ra.criminalmanagementsystem.controller;

import fis.ra.criminalmanagementsystem.dto.TrackEntryDTO;
import fis.ra.criminalmanagementsystem.service.TrackEntryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/trackentry")
@CrossOrigin("*")
public class TrackEntryController {
    private final TrackEntryService trackEntryService;

    public TrackEntryController(TrackEntryService trackEntryService) {
        this.trackEntryService = trackEntryService;
    }

    @PostMapping("/create")
    public ResponseEntity saveTrackEntry(@RequestBody TrackEntryDTO trackEntryDTO) {
        return ResponseEntity.ok(this.trackEntryService.saveTrackEntry(trackEntryDTO));
    }

    @GetMapping("/read")
    public Set<TrackEntryDTO> findAllTrackEntry() {
        return this.trackEntryService.findAllTrackEntry();
    }

    @PutMapping(value = "/update")
    public ResponseEntity updateTrackEntry(@RequestBody TrackEntryDTO trackEntryDTO) {
        return ResponseEntity.ok(trackEntryService.updateTrackEntry(trackEntryDTO));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTrackEntryById(@PathVariable Long id) {
        trackEntryService.deleteTrackEntryById(id);
    }

    @GetMapping("/find/{id}")
    public TrackEntryDTO findTrackEntryById(@PathVariable Long id) {
        return trackEntryService.findTrackEntryById(id);
    }
}
