USE db_SmartAcademy;
CREATE TABLE tbl_Interview(
    fld_InterviewID int IDENTITY (1,1) PRIMARY KEY ,
    fld_InterviewName VARCHAR(30) NOT NULL ,
    fld_Employee_ID INT NOT NULL ,
    FOREIGN KEY (fld_Employee_ID) REFERENCES tbl_Employee(fld_EmployeeID),
    fld_ProductUnderstanding INT,
    fld_ProblemUnderstanding INT,
    fld_Flexibility INT,
    fld_QualityAwareness INT,
    fld_Cooperation INT,
);