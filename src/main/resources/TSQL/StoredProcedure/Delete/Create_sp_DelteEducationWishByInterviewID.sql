CREATE PROCEDURE sp_DeleteEducationWishByInterviewID(@InterviewID INT) AS
    BEGIN
        DELETE FROM tbl_EducationWish WHERE fld_InterviewID = @InterviewID
    END;