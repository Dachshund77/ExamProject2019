-- Find number of param for a stored procedure
CREATE PROCEDURE sp_Get_SP_Meta_Data(@spName VARCHAR(50))
AS
SELECT *
FROM INFORMATION_SCHEMA.PARAMETERS
WHERE SPECIFIC_NAME = @spName;