CREATE or ALTER PROCEDURE sp_Place_Education_Wish(@educationWishID INT OUTPUT , @newAmuNr INT, @newInterviewID INT, @newPriority INT)
AS
BEGIN
    SET NOCOUNT ON
    IF EXISTS(SELECT * FROM tbl_EducationWish WHERE fld_EducationWishID = @educationWishID)
        BEGIN
            UPDATE tbl_EducationWish
            SET fld_AmuNR        = @newAmuNr,
                fld_WishPriority = @newPriority,
                fld_InterviewID = @newInterviewID
            WHERE fld_EducationWishID = @educationWishID
        END
    ELSE
        BEGIN
            INSERT INTO tbl_EducationWish VALUES (@newAmuNr,@newInterviewID, @newPriority)
            SET @educationWishID = SCOPE_IDENTITY()
        END
END;