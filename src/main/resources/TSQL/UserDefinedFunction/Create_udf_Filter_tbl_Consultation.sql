CREATE OR ALTER FUNCTION udf_Filter_tbl_Consultation(@ConsultationID INT, @ConsultationName VARCHAR(50),
                                                     @MinDate DATE, @MaxDate DATE)
    RETURNS TABLE
        AS
        RETURN SELECT *
               FROM tbl_Consultation
               WHERE (@ConsultationID IS NULL OR fld_ConsultationID = @ConsultationID)
                 AND (@ConsultationName IS NULL OR fld_ConsultationName LIKE '%' + @ConsultationName + '%')
                 AND (
                       (@MinDate IS NULL AND @MaxDate IS NULL) OR --both are null
                       ((@MinDate IS NOT NULL AND @MaxDate IS NULL) AND fld_StartDate >= @MinDate AND
                        fld_EndDate >= @MinDate) OR --min has value, max not
                       ((@MinDate IS NULL AND @MaxDate IS NOT NULL) AND fld_StartDate <= @MaxDate AND
                        fld_EndDate <= @MaxDate) OR --min is null, max has value
                       ((@MinDate IS NOT NULL AND @MaxDate IS NOT NULL) AND
                        ((fld_StartDate BETWEEN @MinDate AND @MaxDate) OR
                         (fld_EndDate BETWEEN @MinDate AND @MaxDate)))
                   )
