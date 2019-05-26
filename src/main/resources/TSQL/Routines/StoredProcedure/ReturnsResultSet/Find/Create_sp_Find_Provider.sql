CREATE OR ALTER PROCEDURE sp_Find_Provider(@ProviderID INT,
                                  @ProviderName VARCHAR(30))
AS
BEGIN
    SET NOCOUNT ON
    SELECT fld_ProviderID AS tbl_Provider_PK_fld_ProviderID,
           fld_ProviderName AS tbl_Provider_fld_ProviderName
    FROM udf_Filter_tbl_Provider(@ProviderID, @ProviderName)
END;

