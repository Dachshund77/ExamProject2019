USE db_SmartAcademy;
CREATE PROCEDURE sp_FindProvider(@ProviderID INT,
                                 @ProviderName VARCHAR(30))
AS
BEGIN
    SELECT *
    FROM tbl_Provider
    WHERE (@ProviderID IS NULL OR fld_ProviderID = @ProviderID)
      AND (@ProviderName IS NULL OR fld_ProviderName = @ProviderName)
END;

