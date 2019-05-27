CREATE OR ALTER PROCEDURE sp_Delete_Interview_By_PK(@InterviewID INT) AS
BEGIN
    DELETE FROM tbl_Interview WHERE fld_InterviewID = @InterviewID
END;