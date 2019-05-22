USE db_SmartAcademy;
CREATE PROCEDURE sp_insertCompany_Education_Bridge(@CompanyID INT, @AmuNr INT)
AS
BEGIN
    INSERT INTO tbl_Company_Education_Bridge VALUES (@CompanyID, @AmuNr)
END;