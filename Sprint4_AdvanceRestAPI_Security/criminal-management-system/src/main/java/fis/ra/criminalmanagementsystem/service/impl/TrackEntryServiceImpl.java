package fis.ra.criminalmanagementsystem.service.impl;

import fis.ra.criminalmanagementsystem.controller.globalexceptionhandle.notfoundexception.DetectiveNotFoundException;
import fis.ra.criminalmanagementsystem.controller.globalexceptionhandle.notfoundexception.EvidenceNotFoundException;
import fis.ra.criminalmanagementsystem.controller.globalexceptionhandle.notfoundexception.TrackEntryNotFoundException;
import fis.ra.criminalmanagementsystem.dto.TrackEntryDTO;
import fis.ra.criminalmanagementsystem.model.Detective;
import fis.ra.criminalmanagementsystem.model.Evidence;
import fis.ra.criminalmanagementsystem.model.TrackEntry;
import fis.ra.criminalmanagementsystem.repository.DetectiveRepo;
import fis.ra.criminalmanagementsystem.repository.EvidenceRepo;
import fis.ra.criminalmanagementsystem.repository.TrackEntryRepo;
import fis.ra.criminalmanagementsystem.service.TrackEntryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TrackEntryServiceImpl implements TrackEntryService {

    TrackEntryRepo trackEntryRepo;
    EvidenceRepo evidenceRepo;
    DetectiveRepo detectiveRepo;

    public TrackEntryServiceImpl(TrackEntryRepo trackEntryRepo, EvidenceRepo evidenceRepo, DetectiveRepo detectiveRepo) {
        this.trackEntryRepo = trackEntryRepo;
        this.evidenceRepo = evidenceRepo;
        this.detectiveRepo = detectiveRepo;
    }

    @Override
    @Transactional
    public TrackEntryDTO saveTrackEntry(TrackEntryDTO trackEntryDTO) {
        TrackEntry trackEntry = TrackEntryDTO.Mapper.fromDTO(trackEntryDTO);
        trackEntry.setCreatedAt(LocalDateTime.now());
        trackEntry.setModifiedAt(LocalDateTime.now());
        trackEntryRepo.save(trackEntry);
        return trackEntryDTO;
    }

    @Override
    public Set<TrackEntryDTO> findAllTrackEntry() {
        return this.trackEntryRepo
                .findAll()
                .stream()
                .map(TrackEntryDTO.Mapper::fromEntity)
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public TrackEntryDTO updateTrackEntry(TrackEntryDTO trackEntryDTO) {
        Optional<TrackEntry> optionalTrackEntry = trackEntryRepo.findById(trackEntryDTO.getId());
        if (optionalTrackEntry.isEmpty()) {
            throw new TrackEntryNotFoundException(trackEntryDTO.getId());
        }

        Long detectiveId = trackEntryDTO.getDetective();
        Optional<Detective> detective = detectiveRepo.findById(detectiveId);
        if (detective.isEmpty()) {
            throw new DetectiveNotFoundException(detectiveId);
        }

        Long evidenceId = trackEntryDTO.getEvidence();
        Optional<Evidence> evidence = evidenceRepo.findById(evidenceId);
        if (evidence.isEmpty()) {
            throw new EvidenceNotFoundException(evidenceId);
        }

        TrackEntry newTrackEntry = optionalTrackEntry.get();
        newTrackEntry.setModifiedAt(LocalDateTime.now());
        newTrackEntry.setAction(trackEntryDTO.getAction());
        newTrackEntry.setDate(trackEntryDTO.getDate());
        newTrackEntry.setReason(trackEntryDTO.getReason());
        newTrackEntry.setDetective(detective.get());
        newTrackEntry.setEvidence(evidence.get());

        trackEntryRepo.save(newTrackEntry);

        return trackEntryDTO;
    }

    @Override
    @Transactional
    public void deleteTrackEntryById(Long id) {
        if (trackEntryRepo.findById(id).isEmpty()) {
            throw new TrackEntryNotFoundException(id);
        }
        trackEntryRepo.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteTrackEntry(TrackEntryDTO trackEntryDTO) {
        if (trackEntryRepo.findById(trackEntryDTO.getId()).isEmpty()) {
            throw new TrackEntryNotFoundException(trackEntryDTO.getId());
        }
        trackEntryRepo.delete(TrackEntryDTO.Mapper.fromDTO(trackEntryDTO));
    }

    @Override
    public TrackEntryDTO findTrackEntryById(Long id) {
        Optional<TrackEntry> trackEntryOptional = trackEntryRepo.findById(id);
        if (trackEntryOptional.isEmpty()) {
            throw new TrackEntryNotFoundException(id);
        }
        return TrackEntryDTO.Mapper.fromEntity(trackEntryOptional.get());
    }

    @Override
    public TrackEntryDTO findTrackEntry(TrackEntryDTO trackEntryDTO) {
        Optional<TrackEntry> trackEntryOptional = trackEntryRepo.findById(trackEntryDTO.getId());
        if (trackEntryOptional.isEmpty()) {
            throw new TrackEntryNotFoundException(trackEntryDTO.getId());
        }
        return TrackEntryDTO.Mapper.fromEntity(trackEntryOptional.get());
    }
}
