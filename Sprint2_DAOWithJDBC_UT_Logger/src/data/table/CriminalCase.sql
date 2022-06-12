-- 2nd
CREATE TABLE CriminalCase(

id INT,
version INT NOT NULL,
createdAt DATETIME NOT NULL,
modifiedAt DATETIME NOT NULL,

number VARCHAR(100) UNIQUE NOT NULL,
type ENUM('UNCATEGORIZED', 'INFRACTION', 'MISDEMEANOR', 'FELONY'),
shortDescription VARCHAR(500) NOT NULL,
detailedDescription TEXT NOT NULL,
status ENUM('SUBMITTED', 'UNDER_INVESTIGATION', 'IN_COURT', 'CLOSED', 'DISMISSED', 'COLD'),
note VARCHAR(500) NOT NULL,

evidenceId INT UNIQUE NOT NULL,
leadInvestigatorId INT NOT NULL,
assignedId INT NOT NULL,

CONSTRAINT pk_cc PRIMARY KEY (id, evidenceId, assignedId, leadInvestigatorId),

CONSTRAINT lead_investigator_fk FOREIGN KEY (leadInvestigatorId) REFERENCES Detective(id)
)