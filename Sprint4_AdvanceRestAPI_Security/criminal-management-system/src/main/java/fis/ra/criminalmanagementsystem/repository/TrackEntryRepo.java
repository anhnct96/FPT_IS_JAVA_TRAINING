package fis.ra.criminalmanagementsystem.repository;

import fis.ra.criminalmanagementsystem.model.TrackEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackEntryRepo extends JpaRepository<TrackEntry, Long> {

}
