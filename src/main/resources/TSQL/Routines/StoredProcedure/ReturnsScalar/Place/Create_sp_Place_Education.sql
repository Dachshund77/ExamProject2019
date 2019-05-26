CREATE OR ALTER PROCEDURE sp_Place_Education(@AmuNr INT OUTPUT,
                                    @NewProviderID INT,
                                    @NewEducationName VARCHAR(30),
                                    @newDescription NVARCHAR(MAX),
                                    @newNoOfDays INT)
AS
BEGIN
    SET NOCOUNT ON
    IF EXISTS(SELECT * FROM tbl_Education WHERE fld_AmuNR = @AmuNr)
        BEGIN
            UPDATE tbl_Education
            SET fld_ProviderID    = @NewProviderID,
                fld_EducationName = @NewEducationName,
                fld_Description   = @newDescription,
                fld_NoOfDays      = @newNoOfDays
            WHERE fld_AmuNR = @AmuNr
        END
    ELSE
        BEGIN
            INSERT INTO tbl_Education VALUES (@NewProviderID, @NewEducationName, @newDescription, @newNoOfDays)
        END
    SET @AmuNr = SCOPE_IDENTITY()
END;
