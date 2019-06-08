CREATE OR ALTER PROCEDURE sp_Find_Educations_By_Finished_Education_ID(@FinishedEducationID int)
AS
    BEGIN
        SELECT * FROM tbl_Education
        INNER JOIN tbl_FinishedEducation ON tbl_Education.fld_AmuNR = tbl_FinishedEducation.fld_AmuNR
        WHERE fld_FinishedEducationID =  @FinishedEducationID
    END;