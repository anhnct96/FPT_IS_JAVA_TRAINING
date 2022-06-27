package fis.ra.criminalmanagementsystem.repository;

import fis.ra.criminalmanagementsystem.model.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StorageRepo extends JpaRepository<Storage, Long> {
    Optional<Storage> findByName(String name);
}
