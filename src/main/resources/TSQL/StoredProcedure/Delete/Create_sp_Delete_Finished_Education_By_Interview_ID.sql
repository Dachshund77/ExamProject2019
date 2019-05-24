CREATE PROCEDURE sp_Delete_Finished_Education_By_Interview_ID(@InterviewID INT) AS
    BEGIN
        DELETE FROM tbl_FinishedEducation WHERE fld_InterviewID = @InterviewID
    END;