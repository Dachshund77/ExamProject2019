CREATE OR ALTER PROCEDURE sp_Delete_Consultation_By_PK(@ConsultationID int) AS
BEGIN
    DELETE FROM tbl_Consultation WHERE fld_ConsultationID = @ConsultationID
END;