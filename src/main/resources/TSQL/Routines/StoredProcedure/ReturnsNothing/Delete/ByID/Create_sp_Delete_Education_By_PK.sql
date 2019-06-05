CREATE OR ALTER PROCEDURE sp_Delete_Education_By_PK(@AmurNR INT) AS
BEGIN
    DELETE FROM tbl_Education WHERE fld_AmuNR = @AmurNR
END;