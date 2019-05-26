USE db_SmartAcademy;
CREATE TABLE tbl_Provider
(
    fld_ProviderID INT IDENTITY (1,1) PRIMARY KEY,
    fld_ProviderName VARCHAR(30) NOT NULL
);