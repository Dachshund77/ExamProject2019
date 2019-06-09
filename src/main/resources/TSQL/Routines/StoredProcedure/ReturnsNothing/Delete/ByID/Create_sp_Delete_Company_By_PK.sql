CREATE OR ALTER PROCEDURE sp_Delete_Company_By_PK(@CompanyID INT)
AS
BEGIN
    DELETE FROM tbl_Company WHERE fld_CompanyID = @CompanyID
END;