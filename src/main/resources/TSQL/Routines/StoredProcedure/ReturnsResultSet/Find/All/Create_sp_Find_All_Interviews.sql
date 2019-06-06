CREATE OR ALTER PROCEDURE sp_Find_All_Interviews
AS
BEGIN
    SET NOCOUNT ON
    SELECT [tbl_Interview].fld_InterviewID          AS tbl_Interview_PK_fld_InterviewID,                 --Interview
           [tbl_Interview].fld_InterviewName        AS tbl_Interview_fld_InterviewName,
           [tbl_Interview].fld_ProductUnderstanding AS tbl_Interview_fld_ProductUnderstanding,
           [tbl_Interview].fld_ProblemUnderstanding AS tbl_Interview_fld_ProblemUnderstanding,
           [tbl_Interview].fld_Flexibility          AS tbl_Interview_fld_Flexibility,
           [tbl_Interview].fld_QualityAwareness     AS tbl_Interview_fld_QualityAwarness,
           [tbl_Interview].fld_Cooperation          AS tbl_Interview_fld_Cooperation,

           [tEW].fld_EducationWishID                AS tbl_EducationWish_PK_fld_EducationWishID,         --education wish
           [tEW].fld_AmuNR                          AS tbl_EducationWish_FK_fld_AmuNr,
           [tEW].fld_WishPriority                   AS tbl_EducationWish_fld_WishPriority,

           [tFE].fld_FinishedEducationID            AS tbl_FinishedEducation_PK_fld_FinishedEducationID, --finished education
           [tFE].fld_AmuNR                          AS tbl_FinishedEducation_FK_fld_AmuNr,
           [tFE].fld_FinishedDate                   AS tbl_FinishedEducation_fld_FinishedDate,

           [tE].fld_AmuNR                           AS tbl_Education_PK_fld_AmuNr,                       --Education
           [tE].fld_EducationName                   AS tbl_Education_fld_EducationName,
           [tE].fld_Description                     AS tbl_Education_fld_Description,
           [tE].fld_NoOfDays                        AS tbl_Education_fld_NoOfDays,
           [tE].fld_ProviderID                      AS tbl_Education_FK_fld_ProviderID,

           [tE2].fld_AmuNR                          AS tbl_Education_PK_fld_AmuNr,                       --Education
           [tE2].fld_EducationName                  AS tbl_Education_fld_EducationName,
           [tE2].fld_Description                    AS tbl_Education_fld_Description,
           [tE2].fld_NoOfDays                       AS tbl_Education_fld_NoOfDays,
           [tE2].fld_ProviderID                     AS tbl_Education_FK_fld_ProviderID,

           [tD].fld_Date                            AS tbl_Date_fld_Date,
           [tP].fld_ProviderID                      AS tbl_Provider_PK_fld_ProviderID,                   -- Provider
           [tP].fld_ProviderName                    AS tbl_Provider_fld_ProviderName,

           [tD2].fld_Date                           AS tbl_Date_fld_Date,
           [tP2].fld_ProviderID                     AS tbl_Provider_PK_fld_ProviderID,                   -- Provider
           [tP2].fld_ProviderName                   AS tbl_Provider_fld_ProviderName
    FROM tbl_Interview
             LEFT JOIN tbl_EducationWish tEW ON tbl_Interview.fld_InterviewID = tEW.fld_InterviewID
             LEFT JOIN tbl_FinishedEducation tFE ON tbl_Interview.fld_InterviewID = tFE.fld_InterviewID

             LEFT JOIN tbl_Education tE ON tEW.fld_AmuNR = tE.fld_AmuNR
             LEFT JOIN tbl_Education tE2 ON tFE.fld_AmuNR = tE2.fld_AmuNR

             LEFT JOIN tbl_Date tD ON tE2.fld_AmuNR = tD.fld_AmuNr
             LEFT JOIN tbl_Provider tP ON tE2.fld_ProviderID = tP.fld_ProviderID

             LEFT JOIN tbl_Date tD2 ON tE.fld_AmuNR = tD2.fld_AmuNr
             LEFT JOIN tbl_Provider tP2 ON tE.fld_ProviderID = tP2.fld_ProviderID

END;