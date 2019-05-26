USE db_SmartAcademy;
CREATE TABLE tbl_FinishedEducation
(
    fld_FinishedEducationID INT IDENTITY (1,1) PRIMARY KEY,
    fld_AmuNR               INT NOT NULL,
    FOREIGN KEY (fld_AmuNR) REFERENCES tbl_Education (fld_AmuNR),
    fld_InterviewID INT NOT NULL ,
    FOREIGN KEY (fld_InterviewID) REFERENCES tbl_Interview(fld_InterviewID),
    fld_FinishedDate        DATE
);