USE db_SmartAcademy;
CREATE PROCEDURE sp_DeleteConsultationByCompanyID(@CompanyID INT)
AS
BEGIN
    DELETE FROM tbl_Consultation WHERE fld_CompanyID = @CompanyID
END;