-- Find number of param for a stored procedure
CREATE OR ALTER PROCEDURE sp_Get_SP_Meta_Data(@spName NVARCHAR(50))
AS
SELECT *
FROM INFORMATION_SCHEMA.PARAMETERS
WHERE SPECIFIC_NAME = @spName;