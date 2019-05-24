USE db_SmartAcademy;
CREATE TABLE tbl_Company
(
    fld_CompanyID       INT IDENTITY (1,1) PRIMARY KEY,
    fld_CvrNr           VARCHAR(80)  NOT NULL,
    fld_CompanyName     VARCHAR(50) NOT NULL,
);

