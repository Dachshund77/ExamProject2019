CREATE OR ALTER PROCEDURE  sp_Find_Company_By_ID(@ID INT)
AS
    BEGIN
        SET NOCOUNT ON
        SELECT [tbl_Company].fld_CompanyID   AS CompanyID,
               [tbl_Company].fld_CvrNr       AS CompanyCvrNr,
               [tbl_Company].fld_CompanyName AS CompanyName
        FROM tbl_Company
        WHERE fld_CompanyID = @ID
    END;