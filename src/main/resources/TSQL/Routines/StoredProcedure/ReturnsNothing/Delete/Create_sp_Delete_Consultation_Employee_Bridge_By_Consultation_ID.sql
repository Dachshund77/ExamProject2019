CREATE OR ALTER PROCEDURE sp_Delete_Consultation_Employee_Bridge_By_Consultation_ID(@Consultation INT)
AS
BEGIN
    DELETE
    FROM tbl_Consultation_Employee_Bridge
    WHERE fld_ConsultationID = @Consultation
END;