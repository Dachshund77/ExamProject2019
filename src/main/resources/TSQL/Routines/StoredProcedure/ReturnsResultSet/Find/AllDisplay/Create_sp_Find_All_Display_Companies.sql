CREATE OR ALTER PROCEDURE sp_Fin_All_Display_Companies(@CompanyID INT, @CvrNr VARCHAR(80),
                                                       @CompanyName VARCHAR(50))
AS
    BEGIN
        SET NOCOUNT ON
        SELECT fld_CompanyID AS CompanyID,
               fld_CvrNr AS CompanyCvrNr,
               fld_CompanyName AS CompanyName
        FROM udf_Filter_tbl_Company(@CompanyID, @CvrNr, @CompanyName)
    END;