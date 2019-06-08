CREATE OR ALTER PROCEDURE sp_Find_Provider_By_Education_ID(@EducationID int)
AS
    BEGIN
        SELECT * FROM tbl_Provider
        INNER JOIN tbl_Education ON tbl_Provider.fld_ProviderID = tbl_Education.fld_ProviderID
        WHERE fld_AmuNR = @EducationID
    END;