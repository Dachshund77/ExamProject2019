USE db_SmartAcademy;
CREATE TABLE tbl_Date
(
    fld_DateID INT IDENTITY (1,1) PRIMARY KEY,
    fld_AmuNr  INT  NOT NULL,
    FOREIGN KEY (fld_AmuNr) REFERENCES tbl_Education (fld_AmuNr),
    fld_Date   DATE NOT NULL
);