package fis.ra.repository;

import fis.ra.model.TrackEnTtry;
import fis.ra.model.Evidence;
import fis.ra.model.Storage;

import java.util.Optional;
import java.util.Set;

public interface EvidenceRepo extends AbstractRepo<Evidence> {
    Set<Evidence> findByCriminalCase(TrackEnTtry criminalCase);
    Optional<Evidence> findByNumber(String evidenceNumber);
    boolean isInStorage(Storage storage);
}
