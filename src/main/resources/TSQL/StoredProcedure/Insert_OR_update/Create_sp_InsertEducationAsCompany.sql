USE db_SmartAcademy;
CREATE PROCEDURE sp_InsertEducationAsCompany(@AmuNr INT,
                                    @NewProviderID INT,
                                    @NewEducationName VARCHAR(30),
                                    @newDescription NVARCHAR(MAX),
                                    @newNoOfDays INT,
                                    @CompanyID INT)
AS
BEGIN
    IF EXISTS(SELECT * FROM tbl_Education WHERE fld_AmuNR = @AmuNr)
        BEGIN
            UPDATE tbl_Education
            SET fld_ProviderID    = @NewProviderID,
                fld_EducationName = @NewEducationName,
                fld_Description   = @newDescription,
                fld_NoOfDays      = @newNoOfDays
            WHERE fld_AmuNR = @AmuNr;
            EXECUTE sp_insertCompany_Education_Bridge @CompanyID,@AmuNr;
        END
    ELSE
        BEGIN
            INSERT INTO tbl_Education VALUES (@NewProviderID,@NewEducationName, @newDescription,@newNoOfDays);
            Set @AmuNr = SCOPE_IDENTITY();
            EXECUTE sp_insertCompany_Education_Bridge @CompanyID,@AmuNr;
        END
END;
