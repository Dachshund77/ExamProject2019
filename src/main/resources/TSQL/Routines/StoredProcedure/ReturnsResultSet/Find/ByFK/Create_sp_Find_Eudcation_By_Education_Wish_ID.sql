CREATE OR ALTER PROCEDURE sp_Find_Educations_By_Education_Wish_ID(@EducationWishID int)
AS
    BEGIN
        SELECT * FROM tbl_Education
        INNER JOIN tbl_EducationWish  ON tbl_Education.fld_AmuNR = tbl_EducationWish.fld_AmuNR
        WHERE fld_EducationWishID = @EducationWishID
    END;