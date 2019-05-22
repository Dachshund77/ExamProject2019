CREATE PROCEDURE sp_DeleteFinishedEducationByInterviewID(@InterviewID INT) AS
    BEGIN
        DELETE FROM tbl_FinishedEducation WHERE fld_InterviewID = @InterviewID
    END;