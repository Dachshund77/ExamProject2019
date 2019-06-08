CREATE OR ALTER PROCEDURE sp_Find_Education_By_ID(@ID int)
AS
    BEGIN
        SELECT * FROM tbl_Education
        WHERE fld_AmuNR = @ID
    END;