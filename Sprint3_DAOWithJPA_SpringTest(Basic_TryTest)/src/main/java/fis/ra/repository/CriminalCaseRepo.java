package fis.ra.repository;

import fis.ra.core.CaseStatus;
import fis.ra.core.CaseType;
import fis.ra.model.TrackEnTtry;
import fis.ra.model.Detective;

import java.util.Optional;
import java.util.Set;

public interface CriminalCaseRepo extends  AbstractRepo<TrackEnTtry>  {
    Set<TrackEnTtry> findByLeadInvestigator(Detective detective);
    Optional<TrackEnTtry> findByNumber(String caseNumber);
    Set<TrackEnTtry> findByStatus(CaseStatus status);
    Set<TrackEnTtry> findByType(CaseType type);
}
