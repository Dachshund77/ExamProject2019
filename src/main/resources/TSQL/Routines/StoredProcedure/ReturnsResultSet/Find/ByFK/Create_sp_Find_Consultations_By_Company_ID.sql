CREATE OR ALTER PROCEDURE sp_Find_Consultations_By_Company_ID(@CompanyID int)
AS
BEGIN
    SELECT * FROM tbl_Consultation
    INNER JOIN tbl_Company ON tbl_Consultation.fld_CompanyID = tbl_Company.fld_CompanyID
    WHERE tbl_Company.fld_CompanyID = @CompanyID
END;