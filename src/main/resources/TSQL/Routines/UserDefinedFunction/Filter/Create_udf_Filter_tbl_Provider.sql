CREATE OR ALTER FUNCTION udf_Filter_tbl_Provider(@ProviderID INT, @ProviderName NVARCHAR(30))
    RETURNS TABLE
        AS
        RETURN
        SELECT *
        FROM tbl_Provider
        WHERE (@ProviderID IS NULL OR fld_ProviderID = @ProviderID)
          AND (@ProviderName IS NULL OR fld_ProviderName LIKE '%' + @ProviderName + '%');
