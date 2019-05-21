--USE db_SmartAcademy;
CREATE PROCEDURE sp_InsertCompany(@comp type_Company READONLY)
AS
INSERT INTO tbl_Company(fld_CompanyID, fld_CvrNr, fld_CompanyName, fld_EducationListID)
SELECT [@comp].fld_CompanyID,
       [@comp].fld_CvrNr,
       [@comp].fld_CompanyName,
       [@comp].fld_CompanyID FROM @comp;

