-- 3rd
CREATE TABLE Working_Detective_Case(
detectiveId INT PRIMARY KEY,
criminalCaseId INT PRIMARY KEY,

CONSTRAINT detective_fk FOREIGN KEY (detectiveId) REFERENCES Detective(id),
CONSTRAINT case_fk FOREIGN KEY (criminalCaseId) REFERENCES CriminalCase(id)
)