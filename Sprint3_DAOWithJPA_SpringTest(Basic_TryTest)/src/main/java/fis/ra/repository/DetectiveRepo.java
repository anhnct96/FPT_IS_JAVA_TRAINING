package fis.ra.repository;

import fis.ra.core.Rank;
import fis.ra.model.Detective;

import java.util.Optional;
import java.util.Set;

public interface DetectiveRepo extends AbstractRepo<Detective> {

    Set<Detective> findAll();
    Optional<Detective> findByBadgeNumber(String badgeNumber);
    Set<Detective> findByRank(Rank rank);

    default Optional<Detective> findByIdWithPersonDetails(Long id) {
        return Optional.empty();
    }
}