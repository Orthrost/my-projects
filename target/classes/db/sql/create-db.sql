CREATE TABLE Customer(ID IDENTITY PRIMARY KEY, FirstName VARCHAR(255) NOT NULL, LastName VARCHAR(255) NOT NULL, DateOfBirth DATE NOT NULL, Username VARCHAR(255) UNIQUE, Password VARCHAR(255) NOT NULL);