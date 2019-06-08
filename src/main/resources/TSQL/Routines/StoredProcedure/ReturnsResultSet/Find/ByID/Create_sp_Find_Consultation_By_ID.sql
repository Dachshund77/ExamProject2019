CREATE OR ALTER PROCEDURE sp_Find_Consultation_By_ID(@ID int)
AS
    BEGIN
        SELECT * FROM tbl_Consultation
        WHERE fld_ConsultationID = @ID
    END;