package fis.ra.criminalmanagementsystem.service.impl;

import fis.ra.criminalmanagementsystem.controller.globalexceptionhandle.LeadingCriminalCaseDetectiveException;
import fis.ra.criminalmanagementsystem.controller.globalexceptionhandle.duplicatedexception.DuplicatedBadgeNumberException;
import fis.ra.criminalmanagementsystem.controller.globalexceptionhandle.duplicatedexception.DuplicatedUsernameException;
import fis.ra.criminalmanagementsystem.controller.globalexceptionhandle.notfoundexception.DetectiveNotFoundException;
import fis.ra.criminalmanagementsystem.dto.DetectiveDTO;
import fis.ra.criminalmanagementsystem.model.CriminalCase;
import fis.ra.criminalmanagementsystem.model.Detective;
import fis.ra.criminalmanagementsystem.model.TrackEntry;
import fis.ra.criminalmanagementsystem.repository.CriminalCaseRepo;
import fis.ra.criminalmanagementsystem.repository.DetectiveRepo;
import fis.ra.criminalmanagementsystem.repository.TrackEntryRepo;
import fis.ra.criminalmanagementsystem.repository.authorization.DetectiveDetails;
import fis.ra.criminalmanagementsystem.service.DetectiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DetectiveServiceImpl implements DetectiveService {
    DetectiveRepo detectiveRepo;
    CriminalCaseRepo criminalCaseRepo;
    TrackEntryRepo trackEntryRepo;

    public DetectiveServiceImpl(DetectiveRepo detectiveRepo, CriminalCaseRepo criminalCaseRepo, TrackEntryRepo trackEntryRepo) {
        this.detectiveRepo = detectiveRepo;
        this.criminalCaseRepo = criminalCaseRepo;
        this.trackEntryRepo = trackEntryRepo;
    }

    @Override
    @Transactional
    public DetectiveDTO saveDetective(DetectiveDTO detectiveDTO) {
        if (detectiveRepo.findByUsername(detectiveDTO.getUsername()).isPresent()) {
            throw new DuplicatedUsernameException(detectiveDTO.getUsername());
        }
        if (detectiveRepo.findByBadgeNumber(detectiveDTO.getBadgeNumber()).isPresent()) {
            throw new DuplicatedBadgeNumberException(detectiveDTO.getBadgeNumber());
        }
        Detective detective = DetectiveDTO.Mapper.fromDTO(detectiveDTO);
        detective.setCreatedAt(LocalDateTime.now());
        detective.setModifiedAt(LocalDateTime.now());
        detectiveRepo.save(detective);
        return detectiveDTO;
    }

    @Override
    public Set<DetectiveDTO> findAllDetective() {
        return this.detectiveRepo
                .findAll()
                .stream()
                .map(DetectiveDTO.Mapper::fromEntity)
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public DetectiveDTO updateDetective(DetectiveDTO detectiveDTO) {
        Optional<Detective> optionalDetective = detectiveRepo.findById(detectiveDTO.getId());
        if (optionalDetective.isEmpty()) {
            throw new DetectiveNotFoundException(detectiveDTO.getId());
        }

        if (detectiveRepo.findByUsername(detectiveDTO.getUsername()).isPresent()) {
            throw new DuplicatedUsernameException(detectiveDTO.getUsername());
        }

        if (detectiveRepo.findByBadgeNumber(detectiveDTO.getBadgeNumber()).isPresent()) {
            throw new DuplicatedBadgeNumberException(detectiveDTO.getBadgeNumber());
        }

        Set<CriminalCase> criminalCases = null;
        if (detectiveDTO.getCriminalCases() != null) {
            criminalCases = detectiveDTO.getCriminalCases().stream()
                    .map(criminalCaseRepo::findById)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
        }

        Set<TrackEntry> trackEntries = null;
        if (detectiveDTO.getTrackEntries() != null) {
            trackEntries = detectiveDTO.getTrackEntries().stream()
                    .map(trackEntryRepo::findById)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
        }
        Detective newDetective = optionalDetective.get();

        newDetective.setModifiedAt(LocalDateTime.now());
        newDetective.setUsername(detectiveDTO.getUsername());
        newDetective.setPassword(detectiveDTO.getPassword());
        newDetective.setFirstName(detectiveDTO.getFirstName());
        newDetective.setLastName(detectiveDTO.getLastName());
        newDetective.setHiringDate(detectiveDTO.getHiringDate());
        newDetective.setBadgeNumber(detectiveDTO.getBadgeNumber());
        newDetective.setRank(detectiveDTO.getRank());
        newDetective.setArmed(detectiveDTO.isArmed());
        newDetective.setStatus(detectiveDTO.getStatus());
        newDetective.setCriminalCases(criminalCases);
        newDetective.setTrackEntries(trackEntries);

        detectiveRepo.save(newDetective);

        return detectiveDTO;
    }

    @Override
    @Transactional
    public void deleteDetectiveById(Long id) throws LeadingCriminalCaseDetectiveException {
        if (detectiveRepo.findById(id).isEmpty()) {
            throw new DetectiveNotFoundException(id);
        }
        if (criminalCaseRepo.findByLeadInvestigator_Id(id).isPresent()) {
            throw new LeadingCriminalCaseDetectiveException(id);
        }
        detectiveRepo.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteDetective(DetectiveDTO detectiveDTO) throws LeadingCriminalCaseDetectiveException {
        if (detectiveRepo.findById(detectiveDTO.getId()).isEmpty()) {
            throw new DetectiveNotFoundException(detectiveDTO.getId());
        }
        if (criminalCaseRepo.findByLeadInvestigator_Id(detectiveDTO.getId()).isPresent()) {
            throw new LeadingCriminalCaseDetectiveException(detectiveDTO.getId());
        }
        detectiveRepo.delete(DetectiveDTO.Mapper.fromDTO(detectiveDTO));
    }

    @Override
    public DetectiveDTO findDetectiveById(Long id) {
        Optional<Detective> detectiveOptional = detectiveRepo.findById(id);
        if (detectiveOptional.isEmpty()) {
            throw new DetectiveNotFoundException(id);
        }
        return DetectiveDTO.Mapper.fromEntity(detectiveOptional.get());
    }

    @Override
    public DetectiveDTO findDetective(DetectiveDTO detectiveDTO) {
        Optional<Detective> detectiveOptional = detectiveRepo.findById(detectiveDTO.getId());
        if (detectiveOptional.isEmpty()) {
            throw new DetectiveNotFoundException(detectiveDTO.getId());
        }
        return DetectiveDTO.Mapper.fromEntity(detectiveOptional.get());
    }

    @Override
    public Detective findByUserName(String username) {
        return null;
    }

    @Override
    public DetectiveDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Detective> detective = detectiveRepo.findByUsername(username);
        if (detective.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        return new DetectiveDetails(detective.get());
    }

    @Override
    public DetectiveDetails loadUserById(Long id) throws UsernameNotFoundException {
        Optional<Detective> detective = detectiveRepo.findById(id);
        if (detective.isEmpty()) {
            throw new UsernameNotFoundException("Cant not find this user!");
        }
        return new DetectiveDetails(detective.get());
    }
}
