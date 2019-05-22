CREATE PROCEDURE sp_InsertInterview(@NewInterviewID INT, @EmpFullName INT, @EmpID VARCHAR(30), @ProdRating INT,
                                    @ProbRating INT, @FlexRating INT, @QualRating INT, @CoopRating INT)
AS
BEGIN
    IF EXISTS(SELECT * FROM tbl_Interview WHERE fld_InterviewID = @NewInterViewID)
        BEGIN
            UPDATE tbl_Interview
            SET fld_InterviewName= @EmpFullName,
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
            VALUES (@EmpFullName, @EmpID, @ProdRating, @ProbRating, @FlexRating, @QualRating, @CoopRating)
        end
end;