CREATE OR ALTER PROCEDURE sp_Delete_Provider_By_PK(@ProviderID INT) AS
BEGIN
    DELETE FROM tbl_Provider WHERE fld_ProviderID = @ProviderID
END;