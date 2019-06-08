CREATE OR ALTER PROCEDURE sp_Find_Education_Wishes_By_Interview_ID(@InterviewID int)
AS
BEGIN
    SET NOCOUNT ON
    SELECT [tbl_EducationWish].fld_EducationWishID,
           [tbl_EducationWish].fld_WishPriority
    FROM tbl_EducationWish

             INNER JOIN tbl_Interview ON tbl_EducationWish.fld_InterviewID = tbl_Interview.fld_InterviewID
    WHERE tbl_Interview.fld_InterviewID = @InterviewID
END;