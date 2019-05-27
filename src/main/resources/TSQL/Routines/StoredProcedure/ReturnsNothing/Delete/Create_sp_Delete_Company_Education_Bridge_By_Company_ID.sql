
CREATE OR ALTER PROCEDURE sp_Delete_Company_Education_Bridge_By_Company_ID(@CompanyID INT)
AS
BEGIN
    DELETE
    FROM tbl_Company_Education_Bridge
    WHERE fld_CompanyID = @CompanyID
END;