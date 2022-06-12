-- 4th
CREATE TABLE Storage(

id INT,
version INT NOT NULL,
createdAt DATETIME NOT NULL,
modifiedAt DATETIME NOT NULL,

name VARCHAR(100) NOT NULL,
location VARCHAR (500) NOT NULL,

evidenceId INT UNIQUE NOT NULL,

CONSTRAINT pk_s PRIMARY KEY (id, evidenceId)
)