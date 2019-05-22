USE db_SmartAcademy;
CREATE PROCEDURE sp_InsertDate(@DateID INT, @newAmuNr INT, @newDate DATE) AS
BEGIN
    IF EXISTS(SELECT * FROM tbl_Date WHERE fld_DateID = @DateID)
        BEGIN
            UPDATE tbl_Date
            SET fld_AmuNr = @newAmuNr,
                fld_Date  = @newDate
            WHERE fld_DateID = @DateID
        END
    ELSE
        BEGIN
            INSERT INTO tbl_Date VALUES (@newAmuNr, @DateID)
        END
END;