package fis.ra.criminalmanagementsystem.repository;

import fis.ra.criminalmanagementsystem.model.Evidence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface EvidenceRepo extends JpaRepository<Evidence, Long> {
    Collection<Evidence> findByCriminalCase_Number(String number);

    Optional<Evidence> findByNumber(String number);
}
