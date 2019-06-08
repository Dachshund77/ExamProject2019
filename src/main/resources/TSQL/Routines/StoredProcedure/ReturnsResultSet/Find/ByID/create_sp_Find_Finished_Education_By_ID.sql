CREATE OR ALTER PROCEDURE sp_Find_Finished_Education_By_ID(@ID int)
AS
    BEGIN
        SELECT * FROM tbl_FinishedEducation
        WHERE fld_FinishedEducationID = @ID
    END;