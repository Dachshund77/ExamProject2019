USE db_SmartAcademy;
CREATE PROCEDURE sp_Delete_Date_By_AmuNr(@AmuNr INT) AS
    BEGIN
        DELETE FROM tbl_Date WHERE fld_AmuNr = @AmuNr
    END;