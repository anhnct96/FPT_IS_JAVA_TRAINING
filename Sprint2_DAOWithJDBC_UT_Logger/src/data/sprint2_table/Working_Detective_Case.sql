-- 3rd
CREATE TABLE Working_Detective_Case(
workingId INT,
detectiveId INT,
criminalCaseId INT,

CONSTRAINT pk_wdc PRIMARY KEY (workingId),

CONSTRAINT detective_fk FOREIGN KEY detectiveId REFERENCES Detective(id),
CONSTRAINT case_fk FOREIGN KEY criminalCaseId REFERENCES CriminalCase(id)
)