CREATE OR ALTER FUNCTION udf_Filter_tbl_Company(@CompanyID INT, @CvrNr NVARCHAR(80), @CompanyName NVARCHAR(50))
    RETURNS TABLE
        AS
        RETURN SELECT *
               FROM tbl_Company
               WHERE (@CompanyID IS NULL OR fld_CompanyID = @CompanyID)
                 AND (@CvrNr IS NULL OR fld_CvrNr LIKE '%' + @CvrNr + '%')
                 AND (@CompanyName IS NULL OR fld_CompanyName LIKE '%' + @CompanyName + '%');