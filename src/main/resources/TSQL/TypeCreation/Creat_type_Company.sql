CREATE TYPE type_Company AS TABLE
(
    fld_CompanyID       INT,
    fld_CvrNr           VARCHAR(8)  NOT NULL,
    fld_CompanyName     VARCHAR(50) NOT NULL,
    fld_EducationListID INT,
)