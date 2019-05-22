USE db_SmartAcademy;
CREATE PROCEDURE sp_DeleteDateByAmuNr(@AmuNr INT) AS
    BEGIN
        DELETE FROM tbl_Date WHERE fld_AmuNr = @AmuNr
    END;