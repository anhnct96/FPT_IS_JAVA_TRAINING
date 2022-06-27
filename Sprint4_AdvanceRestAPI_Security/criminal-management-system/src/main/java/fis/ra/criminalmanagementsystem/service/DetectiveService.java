package fis.ra.criminalmanagementsystem.service;

import fis.ra.criminalmanagementsystem.controller.globalexceptionhandle.LeadingCriminalCaseDetectiveException;
import fis.ra.criminalmanagementsystem.dto.DetectiveDTO;
import fis.ra.criminalmanagementsystem.model.Detective;
import fis.ra.criminalmanagementsystem.repository.authorization.DetectiveDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;

public interface DetectiveService extends UserDetailsService {
    DetectiveDTO saveDetective(DetectiveDTO detectiveDTO);

    Set<DetectiveDTO> findAllDetective();

    DetectiveDTO updateDetective(DetectiveDTO detectiveDTO);

    void deleteDetectiveById(Long id) throws LeadingCriminalCaseDetectiveException;

    void deleteDetective(DetectiveDTO detectiveDTO) throws LeadingCriminalCaseDetectiveException;

    DetectiveDTO findDetectiveById(Long id);

    DetectiveDTO findDetective(DetectiveDTO detectiveDTO);

    Detective findByUserName(String username);

    DetectiveDetails loadUserById(Long id);
}
