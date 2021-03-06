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
notes VARCHAR(500) NOT NULL,

leadInvestigatorId INT,

CONSTRAINT pk_cc PRIMARY KEY (id),

CONSTRAINT lead_investigator FOREIGN KEY (leadInvestigatorId) REFERENCES Detective(id)
)
