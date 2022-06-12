-- 5th
CREATE TABLE Evidence (

id INT,
version INT NOT NULL,
createdAt DATETIME NOT NULL,
modifiedAt DATETIME NOT NULL,

number VARCHAR(100) UNIQUE NOT NULL,
itemName VARCHAR(100) NOT NULL,
note VARCHAR(500) NOT NULL,
archived SMAlLINT(1) NOT NULL,

criminalCaseId INT NOT NULL,
trackEntryId INT NOT NULL,
storageId INT NOT NULL,

CONSTRAINT pk_e PRIMARY KEY (id, criminalCaseId, trackEntryId, storageId),

CONSTRAINT storage_fk FOREIGN KEY (storageId) REFERENCES Storage(id),
CONSTRAINT case_fk FOREIGN KEY (criminalCaseId) REFERENCES CriminalCase(id)
)