USE db_SmartAcademy;
CREATE PROCEDURE sp_Find_Provider(@ProviderID INT,
                                 @ProviderName VARCHAR(30))
AS
BEGIN
    SELECT *
    FROM udf_Filter_Provider (@ProviderID,@ProviderName)
END;

