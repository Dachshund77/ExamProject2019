CREATE OR ALTER PROCEDURE sp_Find_Dates_By_Education_ID(@EducationID int)
AS
    BEGIN
        SET NOCOUNT ON
        SELECT fld_Date AS Date
        FROM tbl_Date
        WHERE fld_AmuNr = @EducationID
    END;