//package fis.ra.dto;
//
//import fis.ra.model.CriminalCase;
//import fis.ra.model.Detective;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "working_detective_case")
//public class WorkingDetectiveCase {
//    @EmbeddedId
//    CaseAssignment id;
//
//    @ManyToOne
//    @MapsId("detectiveId")
//    @JoinColumn(name = "detective_id")
//    private Detective assignedDetective;
//
//    @ManyToOne
//    @MapsId("criminalCaseId")
//    @JoinColumn(name = "criminal_case_id")
//    private CriminalCase workingCase;
//}
