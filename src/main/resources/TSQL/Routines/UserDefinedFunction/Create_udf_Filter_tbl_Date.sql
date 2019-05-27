CREATE OR ALTER FUNCTION udf_Filter_tbl_Date(@MinDate DATE, @MaxDate Date)
    RETURNS TABLE
        AS
        RETURN
        SELECT *
        FROM tbl_Date
        WHERE (
                (@MinDate IS NULL AND @MaxDate IS NULL) OR-- both are null
                (@MinDate IS NOT NULL AND @MaxDate IS NULL AND fld_Date >= @MinDate) OR --min has value, max is null
                (@MinDate IS NULL AND @MaxDate IS NOT NULL AND fld_Date <= @MaxDate) OR --min is null, max has value
                (@MinDate IS NOT NULL AND @MaxDate IS NOT NULL AND fld_Date BETWEEN @MinDate AND @MaxDate) -- both have value
            );
