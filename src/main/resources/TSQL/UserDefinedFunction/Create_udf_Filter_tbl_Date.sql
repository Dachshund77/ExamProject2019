CREATE OR ALTER FUNCTION udf_Filter_tbl_Date(@DateID INT, @FirstDate DATE, @SecondDate Date)
    RETURNS TABLE
        AS
        RETURN
        SELECT *
        FROM tbl_Date
        WHERE (@DateID IS NULL OR fld_DateID = @DateID) AND
              (@FirstDate IS NULL OR @SecondDate IS NULL OR fld_Date BETWEEN @FirstDate AND @SecondDate)