CREATE OR ALTER PROCEDURE sp_Find_Education_Wish_By_ID(@ID int)
AS
    BEGIN
        SELECT * FROM tbl_EducationWish
        WHERE fld_EducationWishID = @ID
    END;