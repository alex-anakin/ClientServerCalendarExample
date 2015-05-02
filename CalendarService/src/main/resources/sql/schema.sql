CREATE TABLE Events (
  title VARCHAR (100) NOT NULL,
  description VARCHAR (255),
  id VARCHAR (50) NOT NULL,
  attender VARCHAR (100),
  startDate DATETIME NOT NULL,
  endDate DATETIME NOT NULL,
  PRIMARY KEY(id)
);