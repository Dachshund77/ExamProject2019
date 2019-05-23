USE db_SmartAcademy;
CREATE PROCEDURE sp_DeleteCompany_Education_Bridge_ByCompanyID(@CompanyID INT)
AS
BEGIN
    DELETE
    FROM tbl_Company_Education_Bridge
    WHERE fld_CompanyID = @CompanyID
END;