USE db_SmartAcademy;
CREATE PROCEDURE sp_InsertEducationWish(@educationWishID INT, @newAmuNr INT, @newPriority INT)
AS
BEGIN
    IF EXISTS(SELECT * FROM tbl_EducationWish WHERE fld_EducationWishID = @educationWishID)
        BEGIN
            UPDATE tbl_EducationWish
            SET fld_AmuNR        = @newAmuNr,
                fld_WishPriority = @newPriority
            WHERE fld_EducationWishID = @educationWishID
        END
    ELSE
        BEGIN
            INSERT INTO tbl_EducationWish VALUES (@newAmuNr, @newPriority)
        END
END;