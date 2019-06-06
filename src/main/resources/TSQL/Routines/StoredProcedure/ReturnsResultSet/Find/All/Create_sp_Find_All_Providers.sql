CREATE OR ALTER PROCEDURE sp_Find_All_Providers
AS
BEGIN
    SET NOCOUNT ON
    SELECT fld_ProviderID   AS tbl_Provider_PK_fld_ProviderID,
           fld_ProviderName AS tbl_Provider_fld_ProviderName
    FROM tbl_Provider

END;