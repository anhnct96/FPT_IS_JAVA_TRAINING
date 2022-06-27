package fis.ra.criminalmanagementsystem.repository;

import fis.ra.criminalmanagementsystem.core.CaseStatus;
import fis.ra.criminalmanagementsystem.model.CriminalCase;
import fis.ra.criminalmanagementsystem.model.Detective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface CriminalCaseRepo extends JpaRepository<CriminalCase, Long> {
    Collection<CriminalCase> findByStatus(CaseStatus status);
    Collection<CriminalCase> findByAssigned_Username (String username);
    Optional<CriminalCase> findByNumber(String number);
    Optional<CriminalCase> findByLeadInvestigator_Id (Long id);
}
