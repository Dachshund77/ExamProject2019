CREATE OR ALTER PROCEDURE sp_Find_Education_By_ID(@ID int)
AS
    BEGIN
        SET NOCOUNT ON
        SELECT [tbl_Education].fld_AmuNR         AS EducationAmuNr,
               [tbl_Education].fld_EducationName AS EducationName,
               [tbl_Education].fld_Description   AS EducationDescription,
               [tbl_Education].fld_NoOfDays      AS EducationNoOfDays
        FROM tbl_Education
        WHERE fld_AmuNR = @ID
    END;