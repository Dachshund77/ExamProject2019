CREATE OR ALTER PROCEDURE sp_Find_All_Display_Interviews(@fld_InterviewID INT,
                                                         @fld_InterviewName VARCHAR(30))
AS
BEGIN
    SET NOCOUNT ON
     SELECT fld_InterviewID AS InterviewID,
           fld_InterviewName AS InterviewName,
           fld_ProductUnderstanding AS InterviewProductUnderstanding,
           fld_ProblemUnderstanding AS interviewProblemUnderstanding,
           fld_Flexibility AS InterviewFlexiblity,
           fld_QualityAwareness AS InterviewQualityAwarenes,
           fld_Cooperation AS InterviewCooperation
    FROM udf_Filter_tbl_Interview(@fld_InterviewID, @fld_InterviewName)



END;