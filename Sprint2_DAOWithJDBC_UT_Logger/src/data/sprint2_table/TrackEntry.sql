-- 6th
CREATE TABLE TrackEntry(

id INT,
version INT NOT NULL,
createdAt DATETIME NOT NULL,
modifiedAt DATETIME NOT NULL,

trackDate DATETIME NOT NULL,
action ENUM('SUBMITTED','RETRIEVED','RETURNED'),
reason VARCHAR(100) NOT NULL,

detectiveId INT,
evidenceId INT,

CONSTRAINT d_fk FOREIGN KEY detectiveId REFERENCES Detective(id),
CONSTRAINT e_fk FOREIGN KEY evidenceId REFERENCES Evidence(id),

CONSTRAINT pk_te PRIMARY KEY (id)
)