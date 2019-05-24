USE db_SmartAcademy;
CREATE PROCEDURE sp_Delete_Consultation_By_Company_ID(@CompanyID INT)
AS
BEGIN
    DELETE FROM tbl_Consultation WHERE fld_CompanyID = @CompanyID
END;