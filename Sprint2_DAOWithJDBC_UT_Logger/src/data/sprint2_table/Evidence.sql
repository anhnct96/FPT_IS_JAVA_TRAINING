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

criminalCaseId INT,
storageId INT,

CONSTRAINT pk_e PRIMARY KEY (id),

CONSTRAINT cc_fk FOREIGN KEY criminalCaseId REFERENCES CriminalCase(id),
CONSTRAINT s_fk FOREIGN KEY storageId REFERENCES Storage(id),
)