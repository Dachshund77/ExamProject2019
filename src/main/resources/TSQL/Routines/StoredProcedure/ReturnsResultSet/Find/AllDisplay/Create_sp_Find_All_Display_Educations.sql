CREATE OR ALTER PROCEDURE sp_Find_All_Display_Educations(@AmuNr INT,
                                                         @EducationName NVARCHAR(30), @NoOfDays INT)
AS
BEGIN
    SET NOCOUNT ON
    DECLARE
        @tbl_Education AS TableType_Education
    INSERT INTO @tbl_Education
    SELECT fld_AmuNR, fld_ProviderID, fld_EducationName, fld_Description, fld_NoOfDays
    FROM udf_Filter_tbl_Education(@AmuNR, @EducationName, @NoOfDays)


    SELECT fld_AmuNR as EducationAmuNr,
           fld_EducationName as EducationName,
           fld_Description AS EducationDescription,
           fld_NoOfDays As EducationNoOfDays,
           fld_ProviderName AS ProviderName
    FROM @tbl_Education
    LEFT JOIN tbl_Provider ON [@tbl_Education].fld_ProviderID = [tbl_Provider].fld_ProviderID
END;