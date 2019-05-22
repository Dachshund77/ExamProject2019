CREATE PROCEDURE sp_InsertProvider(@ProviderID INT,
                                   @newProviderName VARCHAR(30))
AS
BEGIN
    IF EXISTS(SELECT * FROM tbl_Provider WHERE fld_ProviderID = @ProviderID)
        BEGIN
            UPDATE tbl_Provider
            SET fld_ProviderName = @newProviderName
            WHERE fld_ProviderName = @ProviderID
        END
    ELSE
        BEGIN
            INSERT INTO tbl_Provider VALUES (@newProviderName)
        END
END;

