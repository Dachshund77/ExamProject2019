CREATE OR ALTER PROCEDURE sp_Find_All_Educations
AS
BEGIN
    SET NOCOUNT ON
    SELECT [tbl_Date].fld_Date               AS tbl_Date_fld_Date,

           [tbl_Provider].fld_ProviderID     AS tbl_Provider_PK_fld_ProviderID,
           [tbl_Provider].fld_ProviderName   AS tbl_Provider_fld_ProviderName,

           [tbl_Education].fld_EducationName AS tbl_Education_fld_EducationName,
           [tbl_Education].fld_AmuNR         AS tbl_Education_PK_fld_AmuNr,
           [tbl_Education].fld_Description   AS tbl_Education_fld_Description,
           [tbl_Education].fld_NoOfDays      AS tbl_Education_fld_NoOfDays,
           [tbl_Education].fld_ProviderID    AS tbl_Education_FK_fld_ProviderID
    FROM tbl_Education
             LEFT JOIN tbl_Date ON tbl_Education.fld_AmuNR = tbl_Date.fld_AmuNr
             LEFT JOIN tbl_Provider ON tbl_Education.fld_ProviderID = tbl_Provider.fld_ProviderID
END;