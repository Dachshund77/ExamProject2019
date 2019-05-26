CREATE OR ALTER PROCEDURE sp_Place_Finished_Education(@ID INT OUTPUT, @newAmuNr INT, @newInterviewID INT,
                                                      @newFinishedDate DATE)
AS
BEGIN
    SET NOCOUNT ON
    IF EXISTS(SELECT * FROM tbl_FinishedEducation WHERE fld_FinishedEducationID = @ID)
        BEGIN
            UPDATE tbl_FinishedEducation
            SET fld_AmuNR        = @newAmuNr,
                fld_FinishedDate = @newFinishedDate,
                fld_InterViewID  = @newInterviewID
            WHERE fld_FinishedEducationID = @ID
        END
    ELSE
        BEGIN
            INSERT INTO tbl_FinishedEducation VALUES (@newAmuNr, @newInterviewID, @newFinishedDate)
            SET @ID = SCOPE_IDENTITY()
        END
END;