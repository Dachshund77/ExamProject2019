CREATE OR ALTER PROCEDURE sp_Find_Education(@AmuNr INT, @EducationName VARCHAR(30), @NoOfDays INT,
                                            @DateMinDate DATE, @DateMaxDate DATE, @ProviderID INT,
                                            @ProviderName VARCHAR(30))
AS
BEGIN
    SET NOCOUNT ON
    DECLARE
        @tbl_Education AS TableType_Education
    INSERT INTO @tbl_Education
    SELECT fld_AmuNR, fld_ProviderID, fld_EducationName, fld_Description, fld_NoOfDays
    FROM udf_Filter_tbl_Education(@AmuNR, @EducationName, @NoOfDays)

    DECLARE
        @tbl_Date AS TableType_Date
    INSERT INTO @tbl_Date
    SELECT fld_DateID, fld_AmuNr, fld_Date
    FROM udf_Filter_tbl_Date(@DateMinDate, @DateMaxDate)

    DECLARE
        @tbl_Provider AS TableType_Provider
    INSERT INTO @tbl_Provider
    SELECT fld_ProviderID, fld_ProviderName
    FROM udf_Filter_tbl_Provider(@ProviderID, @ProviderName)

    SELECT [@tbl_Date].fld_Date               AS tbl_Date_fld_Date,
           [@tbl_Provider].fld_ProviderID     AS tbl_Provider_PK_fld_ProviderID, -- Provider
           [@tbl_Provider].fld_ProviderName   AS tbl_Provider_fld_ProviderName,
           [@tbl_Education].fld_EducationName AS tbl_Education_fld_EducationName,
           [@tbl_Education].fld_AmuNR         AS tbl_Education_PK_fld_AmuNr,
           [@tbl_Education].fld_Description   AS tbl_Education_fld_Description,
           [@tbl_Education].fld_NoOfDays      AS tbl_Education_fld_NoOfDays,
           [@tbl_Education].fld_ProviderID    AS tbl_Education_FK_fld_ProviderID

    FROM @tbl_Education
             INNER JOIN @tbl_Date ON [@tbl_Education].fld_AmuNR = [@tbl_Date].fld_AmuNr
             INNER JOIN @tbl_Provider ON [@tbl_Education].fld_ProviderID = [@tbl_Provider].fld_ProviderID
END;