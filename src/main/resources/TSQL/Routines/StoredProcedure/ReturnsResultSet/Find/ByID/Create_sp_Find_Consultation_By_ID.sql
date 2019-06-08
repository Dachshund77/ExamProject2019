CREATE OR ALTER PROCEDURE sp_Find_Consultation_By_ID(@ID int)
AS
    BEGIN
        SET NOCOUNT ON
        SELECT [tbl_Consultation].fld_ConsultationID   AS ConsultationID,
               [tbl_Consultation].fld_ConsultationName AS ConsultationName,
               [tbl_Consultation].fld_StartDate        AS ConsultationStartDate,
               [tbl_Consultation].fld_EndDate          AS ConsultationEndDate
        FROM tbl_Consultation
        WHERE fld_ConsultationID = @ID
    END;