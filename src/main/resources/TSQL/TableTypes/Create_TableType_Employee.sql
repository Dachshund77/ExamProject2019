CREATE TYPE TableType_Employee AS TABLE
(
    fld_EmployeeID        INT PRIMARY KEY,
    fld_EmployeeFirstName VARCHAR(30),
    fld_EmployeeLastName  VARCHAR(30),
    fld_CprNr             VARCHAR(10) NOT NULL,
    fld_Email             VARCHAR(30),
    fld_PhoneNr           VARCHAR(20),
)