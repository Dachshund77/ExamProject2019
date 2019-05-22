USE db_SmartAcademy;
CREATE PROCEDURE sp_InsertFinishedEducation(@ID INT, @newAmuNr INT, @newFinishedDate DATE)
AS
BEGIN
    IF EXISTS(SELECT * FROM tbl_FinishedEducation WHERE fld_FinishedEducationID = @ID)
        BEGIN
            UPDATE tbl_FinishedEducation
            SET fld_AmuNR        = @newAmuNr,
                fld_FinishedDate = @newFinishedDate
            WHERE fld_FinishedEducationID = @ID
        END
    ELSE
        BEGIN
            INSERT INTO tbl_FinishedEducation VALUES (@newAmuNr, @newFinishedDate)
        END
END;