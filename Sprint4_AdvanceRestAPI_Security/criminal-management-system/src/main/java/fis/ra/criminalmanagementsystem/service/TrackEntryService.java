package fis.ra.criminalmanagementsystem.service;

import fis.ra.criminalmanagementsystem.dto.TrackEntryDTO;

import java.util.Set;

public interface TrackEntryService {
    TrackEntryDTO saveTrackEntry(TrackEntryDTO trackEntryDTO);

    Set<TrackEntryDTO> findAllTrackEntry();

    TrackEntryDTO updateTrackEntry(TrackEntryDTO trackEntryDTO);

    void deleteTrackEntryById(Long id);

    void deleteTrackEntry(TrackEntryDTO trackEntryDTO);

    TrackEntryDTO findTrackEntryById(Long id);

    TrackEntryDTO findTrackEntry(TrackEntryDTO trackEntryDTO);
}
