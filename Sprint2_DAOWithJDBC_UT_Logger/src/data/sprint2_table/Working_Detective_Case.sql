-- 3rd
CREATE TABLE Working_Detective_Case(
workingId INT,
detectiveId INT,
criminalCaseId INT,
CONSTRAINT pk_te PRIMARY KEY (id),
CONSTRAINT detective_fk FOREIGN KEY detectiveId REFERENCES Detective(id),
CONSTRAINT case_fk FOREIGN KEY criminalCaseId REFERENCES CriminalCase(id),
)