CREATE PROCEDURE sp_Delete_Education_Wish_By_Interview_ID(@InterviewID INT) AS
    BEGIN
        DELETE FROM tbl_EducationWish WHERE fld_InterviewID = @InterviewID
    END;