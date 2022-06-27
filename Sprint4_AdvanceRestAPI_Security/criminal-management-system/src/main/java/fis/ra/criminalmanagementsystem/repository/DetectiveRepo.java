package fis.ra.criminalmanagementsystem.repository;

import fis.ra.criminalmanagementsystem.model.Detective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DetectiveRepo extends JpaRepository<Detective, Long> {
    Optional<Detective> findByUsername(String username);

    Optional<Detective> findByBadgeNumber(String badgeNumber);
}
