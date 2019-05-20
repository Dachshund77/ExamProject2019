USE db_SmartAcademy;
CREATE TABLE tbl_FinishedEducation_Interview_Bridge
(
    fld_FinishedEducationID INT NOT NULL,
    FOREIGN KEY (fld_FinishedEducationID) REFERENCES tbl_FinishedEducation (fld_FinishedEducationID) ON DELETE CASCADE,
    fld_InterViewID         INT NOT NULL,
    FOREIGN KEY (fld_InterViewID) REFERENCES tbl_Interview (fld_InterViewID) ON DELETE CASCADE
);