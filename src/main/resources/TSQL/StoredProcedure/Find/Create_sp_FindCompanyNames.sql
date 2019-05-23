USE db_SmartAcademy;
CREATE PROCEDURE sp_FindCompanyNames
AS
	BEGIN
		SELECT fld_CompanyName FROM tbl_Company
    END;