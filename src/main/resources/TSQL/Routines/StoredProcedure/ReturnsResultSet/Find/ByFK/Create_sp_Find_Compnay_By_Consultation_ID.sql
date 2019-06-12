CREATE OR ALTER PROCEDURE sp_Find_Company_By_Consultation_ID(@ConsultationID Int)
AS
BEGIN
    SELECT [tbl_Company].fld_CompanyID   AS CompanyID,
           [tbl_Company].fld_CvrNr       AS CompanyCvrNr,
           [tbl_Company].fld_CompanyName AS CompanyName
    FROM tbl_Company
             INNER JOIN tbl_Consultation ON tbl_Company.fld_CompanyID = tbl_Consultation.fld_CompanyID
    WHERE tbl_Consultation.fld_ConsultationID = @ConsultationID
END;