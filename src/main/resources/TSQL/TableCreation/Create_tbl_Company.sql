CREATE TABLE tbl_Company
(
    fld_CompanyID       INT IDENTITY (1,1) PRIMARY KEY,
    fld_CvrNr           VARCHAR(8)  NOT NULL,
    fld_CompanyName     VARCHAR(50) NOT NULL,
    fld_EducationListID INT,
    FOREIGN KEY (fld_EducationListID) REFERENCES tbl_EducationList (fld_EducationListID)
);

