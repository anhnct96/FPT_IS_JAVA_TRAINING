-- 3rd
CREATE TABLE Working_Detective_Case(
detectiveId INT,
criminalCaseId INT,

CONSTRAINT pk_wdc PRIMARY KEY (detectiveId, criminalCaseId),

CONSTRAINT case_id_fk FOREIGN KEY (criminalCaseId) REFERENCES CriminalCase(id),
CONSTRAINT detective_id_fk FOREIGN KEY (detectiveId) REFERENCES Detective(id)
)