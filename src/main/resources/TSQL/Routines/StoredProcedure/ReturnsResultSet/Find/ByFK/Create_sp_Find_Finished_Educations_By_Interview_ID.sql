CREATE OR ALTER PROCEDURE sp_Finished_Educations_By_Interview_ID(@InterviewID int)
AS
    BEGIN
        SELECT * FROM tbl_FinishedEducation
        INNER JOIN tbl_Interview  ON tbl_FinishedEducation.fld_InterviewID = tbl_Interview.fld_InterviewID
        WHERE tbl_Interview.fld_InterviewID = @InterviewID
    END;