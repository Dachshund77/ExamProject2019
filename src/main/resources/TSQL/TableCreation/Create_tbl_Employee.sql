USE db_SmartAcademy;
CREATE TABLE tbl_Employee
(
    fld_EmployeeID        INT IDENTITY (1,1) PRIMARY KEY,
    fld_EmployeeFirstName VARCHAR(30),
    fld_EmployeeLastName  VARCHAR(30),
    fld_CprNr             VARCHAR(10) NOT NULL,
    fld_Email VARCHAR(30),
    fld_PhoneNr VARCHAR(20),
    fld_ConsultationID         INT         NOT NULL,
    FOREIGN KEY (fld_ConsultationID) REFERENCES tbl_Consultation (fld_ConsultationID)
);