CREATE OR ALTER PROCEDURE sp_Find_Interview_By_ID(@ID int)
AS
    BEGIN
        SELECT * FROM tbl_Interview
        WHERE fld_InterviewID = @ID
    END;