CREATE OR ALTER PROCEDURE sp_Find_Interview_By_ID(@ID int)
AS
    BEGIN
        SET NOCOUNT ON
        SELECT [tbl_Interview].fld_InterviewID AS InterviewID,
               [tbl_Interview].fld_InterviewName AS InterviewName,
               [tbl_Interview].fld_ProductUnderstanding AS InterviewProductUnderstanding,
               [tbl_Interview].fld_ProblemUnderstanding AS interviewProblemUnderstanding,
               [tbl_Interview].fld_Flexibility AS InterviewFlexiblity,
               [tbl_Interview].fld_QualityAwareness AS InterviewQualityAwarenes,
               [tbl_Interview].fld_Cooperation AS InterviewCooperation
        FROM tbl_Interview
        WHERE fld_InterviewID = @ID
    END;