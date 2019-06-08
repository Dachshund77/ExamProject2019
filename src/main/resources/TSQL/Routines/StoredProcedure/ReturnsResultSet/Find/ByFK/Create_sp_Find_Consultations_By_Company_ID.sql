CREATE OR ALTER PROCEDURE sp_Find_Consultations_By_Company_ID(@CompanyID int)
AS
BEGIN
    SET NOCOUNT ON
    SELECT [tbl_Consultation].fld_ConsultationID   AS ConsultationID,
           [tbl_Consultation].fld_ConsultationName AS ConsultationName,
           [tbl_Consultation].fld_StartDate        AS ConsultationStartDate,
           [tbl_Consultation].fld_EndDate          AS ConsultationEndDate
    FROM tbl_Consultation
             INNER JOIN tbl_Company ON tbl_Consultation.fld_CompanyID = tbl_Company.fld_CompanyID
    WHERE tbl_Company.fld_CompanyID = @CompanyID
END;