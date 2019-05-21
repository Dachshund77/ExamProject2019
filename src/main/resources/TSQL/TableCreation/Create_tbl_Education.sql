USE db_SmartAcademy;
CREATE TABLE tbl_Education
(
    fld_AmuNR        Int IDENTITY (1,1) PRIMARY KEY,
    fld_ProviderID  int         NOT NULL,
    fld_EducationName VARCHAR(30) NOT NULL,
    fld_Description  NVARCHAR(MAX),
    fld_NoOfDays     INT         NOT NULL,
    FOREIGN KEY (fld_ProviderID) REFERENCES tbl_Provider (fld_ProviderID)
);