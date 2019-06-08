CREATE OR ALTER PROCEDURE sp_Find_Provider_By_ID(@ID int)
AS
    BEGIN
        SELECT * FROM tbl_Provider
        WHERE fld_ProviderID = @ID
    END;