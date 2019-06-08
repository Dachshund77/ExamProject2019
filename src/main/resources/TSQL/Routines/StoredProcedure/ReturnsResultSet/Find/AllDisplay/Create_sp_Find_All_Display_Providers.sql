CREATE OR ALTER PROCEDURE sp_Find_All_Display_Providers(@ProviderID INT,
                                          @ProviderName VARCHAR(30))
AS
BEGIN
    SET NOCOUNT ON

    SELECT fld_ProviderID AS ProviderID,
           fld_ProviderName AS ProviderName
    FROM udf_Filter_tbl_Provider(@ProviderID, @ProviderName)
END;