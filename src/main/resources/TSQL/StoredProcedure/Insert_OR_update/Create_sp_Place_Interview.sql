CREATE OR ALTER PROCEDURE sp_Place_Interview(@NewInterviewID INT OUTPUT , @interviewName VARCHAR(30), @EmpID INT, @ProdRating INT,
                                    @ProbRating INT, @FlexRating INT, @QualRating INT, @CoopRating INT)
AS
BEGIN
    SET NOCOUNT ON
    IF EXISTS(SELECT * FROM tbl_Interview WHERE fld_InterviewID = @NewInterViewID)
        BEGIN
            UPDATE tbl_Interview
            SET fld_InterviewName= @interviewName,
                fld_Employee_ID = @EmpID,
                fld_ProductUnderstanding = @ProdRating,
                fld_ProblemUnderstanding = @ProbRating,
                fld_Flexibility = @FlexRating,
                fld_QualityAwareness = @QualRating,
                fld_Cooperation = @CoopRating
            WHERE fld_InterviewID = @NewInterviewID
        END
    ELSE
        BEGIN
            INSERT INTO tbl_Interview
            VALUES (@EmpID, @interviewName, @ProdRating, @ProbRating, @FlexRating, @QualRating, @CoopRating)
            SET @NewInterviewID = SCOPE_IDENTITY()
        end
end;