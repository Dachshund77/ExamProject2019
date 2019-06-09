CREATE OR ALTER PROCEDURE sp_Find_Provider_By_ID(@ID int)
AS
BEGIN
    SET NOCOUNT ON
    SELECT [tbl_Provider].fld_ProviderID   AS ProviderID,
           [tbl_Provider].fld_ProviderName AS ProviderName
    FROM tbl_Provider
    WHERE fld_ProviderID = @ID
END;