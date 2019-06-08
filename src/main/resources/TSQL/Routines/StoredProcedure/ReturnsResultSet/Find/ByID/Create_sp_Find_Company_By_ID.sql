CREATE OR ALTER PROCEDURE  sp_Find_Company_By_ID(@ID INT)
AS
    BEGIN
        SELECT *
        FROM tbl_Company
        WHERE fld_CompanyID = @ID
    END;