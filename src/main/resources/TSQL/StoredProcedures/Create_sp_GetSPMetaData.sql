-- Find number of param for a stored procedure
USE db_SmartAcademy;
CREATE PROCEDURE sp_GetSPMetaData(@spName VARCHAR(50))
AS
SELECT *
FROM INFORMATION_SCHEMA.PARAMETERS
WHERE SPECIFIC_NAME = @spName;