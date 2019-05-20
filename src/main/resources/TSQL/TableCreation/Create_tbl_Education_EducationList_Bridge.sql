USE db_SmartAcademy;
CREATE TABLE tbl_Education_EducationList_Bridge
(
    fld_AmuNR           INT NOT NULL,
    FOREIGN KEY (fld_AmuNR) REFERENCES tbl_Education (fld_AmuNR) ON DELETE CASCADE,
    Fld_EducationListID INT NOT NULL,
    FOREIGN KEY (Fld_EducationListID) REFERENCES tbl_EducationList (Fld_EducationListID) ON DELETE CASCADE
);