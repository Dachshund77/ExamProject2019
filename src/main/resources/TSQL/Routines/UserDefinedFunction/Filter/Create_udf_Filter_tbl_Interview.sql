CREATE OR ALTER FUNCTION udf_Filter_tbl_Interview(@fld_InterviewID INT, @fld_InterviewName VARCHAR(30))
    RETURNS TABLE
        AS
        RETURN
        SELECT *
        FROM tbl_Interview
        WHERE (@fld_InterviewID IS NULL OR fld_InterviewID = @fld_InterviewID)
          AND (@fld_InterviewName IS NULL OR fld_InterviewName LIKE '%' + @fld_InterviewName + '%');