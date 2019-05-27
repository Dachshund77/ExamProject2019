CREATE OR ALTER PROCEDURE sp_Place_Consultation(@ConsultationID INT OUTPUT ,
                                       @ConsultationName VARCHAR(50),
                                       @StartDate DATE,
                                       @EndDate DATE,
                                       @CompanyID INT)
AS
BEGIN
    SET NOCOUNT ON
    IF EXISTS(SELECT * FROM tbl_Consultation WHERE fld_ConsultationID = @ConsultationID)
        BEGIN
            UPDATE tbl_Consultation
            SET fld_ConsultationName = @ConsultationName,
                fld_StartDate        = @StartDate,
                fld_EndDate          = @EndDate,
                fld_CompanyID        = @CompanyID
            WHERE @ConsultationID = @ConsultationID
        END
    ELSE
        BEGIN
            INSERT INTO tbl_Consultation VALUES (@ConsultationName, @StartDate, @EndDate, @CompanyID)
            SET @ConsultationID = SCOPE_IDENTITY()
        END
END;