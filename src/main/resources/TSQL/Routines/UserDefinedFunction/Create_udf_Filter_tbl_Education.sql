CREATE OR ALTER FUNCTION udf_Filter_tbl_Education(@AmuNR INT, @EducationName VARCHAR(30), @NoOfDays INT)
    RETURNS TABLE
        AS
        RETURN
        SELECT *
        FROM tbl_Education
        WHERE (@AmuNR IS NULL OR fld_AmuNR = @AmuNR) AND
              (@EducationName IS NULL OR fld_EducationName LIKE '%'+@EducationName+'%') AND
              (@NoOfDays IS NULL OR fld_NoOfDays = @NoOfDays);