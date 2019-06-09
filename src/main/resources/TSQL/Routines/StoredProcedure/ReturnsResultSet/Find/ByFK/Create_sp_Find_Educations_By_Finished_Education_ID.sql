CREATE OR ALTER PROCEDURE sp_Find_Educations_By_Finished_Education_ID(@FinishedEducationID int)
AS
    BEGIN
        SET NOCOUNT ON
        SELECT [tbl_Education].fld_AmuNR         AS EducationAmuNr,
               [tbl_Education].fld_EducationName AS EducationName,
               [tbl_Education].fld_Description   AS EducationDescription,
               [tbl_Education].fld_NoOfDays      AS EducationNoOfDays
        FROM tbl_Education
        INNER JOIN tbl_FinishedEducation ON tbl_Education.fld_AmuNR = tbl_FinishedEducation.fld_AmuNR
        WHERE fld_FinishedEducationID =  @FinishedEducationID
    END;