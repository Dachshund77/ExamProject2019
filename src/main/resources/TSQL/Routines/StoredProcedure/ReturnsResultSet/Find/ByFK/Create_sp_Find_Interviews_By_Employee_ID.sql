CREATE OR ALTER PROCEDURE sp_Find_Interviews_By_Employee_ID(@EmployeeID int)
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
        INNER JOIN tbl_Employee ON tbl_Interview.fld_Employee_ID = tbl_Employee.fld_EmployeeID
        WHERE fld_EmployeeID = @EmployeeID
    END;