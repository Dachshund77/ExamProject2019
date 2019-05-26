CREATE OR ALTER PROCEDURE sp_Insert_Company_Education_Bridge(@CompanyID INT, @AmuNr INT)
AS
BEGIN
    INSERT INTO tbl_Company_Education_Bridge VALUES (@CompanyID, @AmuNr)
END;