CREATE OR ALTER PROCEDURE sp_Find_Provider(@ProviderID INT,
                                  @ProviderName VARCHAR(30))
AS
BEGIN
    SELECT fld_ProviderID AS tbl_Provider_PK_fld_ProviderID,
           fld_ProviderName AS tbl_Provider_fld_ProviderName
    FROM udf_Filter_Provider(@ProviderID, @ProviderName)
END;

