CREATE OR ALTER PROCEDURE sp_Find_Education(@AmuNr INT, @EducationName VARCHAR(30), @NoOfDays INT, @DateID INT,
                                            @DateMinDate DATE, @DateMaxDate DATE, @ProviderID INT,
                                            @ProviderName VARCHAR(30))
AS
BEGIN
    DECLARE
        @tbl_Education AS TableType_Education
    INSERT INTO @tbl_Education
    SELECT fld_AmuNR, fld_ProviderID, fld_EducationName, fld_Description, fld_NoOfDays
    FROM udf_Filter_tbl_Education(@AmuNR, @EducationName, @NoOfDays)

    DECLARE
        @tbl_Date AS TableType_Date
    INSERT INTO @tbl_Date
    SELECT fld_DateID, fld_AmuNr, fld_Date
    FROM udf_Filter_Date(@DateID, @DateMinDate, @DateMaxDate)

    DECLARE
        @tbl_Provider AS TableType_Provider
    INSERT INTO @tbl_Provider
    SELECT fld_ProviderID, fld_ProviderName
    FROM udf_Filter_Provider(@ProviderID, @ProviderName)

    SELECT [@tbl_Date].fld_DateID           AS tbl_Date_PK_fld_DateID,         -- Date of educations
           [@tbl_Date].fld_Date             AS tbl_Date_fld_Date,
           [@tbl_Date].fld_AmuNr            AS tbl_Date_FK_fld_AmuNr,
           [@tbl_Provider].fld_ProviderID   AS tbl_Provider_PK_fld_ProviderID, -- Provider
           [@tbl_Provider].fld_ProviderName AS tbl_Provider_fld_ProviderName

    FROM @tbl_Education
             INNER JOIN @tbl_Date ON [@tbl_Education].fld_AmuNR = [@tbl_Date].fld_AmuNr
             INNER JOIN @tbl_Provider ON [@tbl_Education].fld_ProviderID = [@tbl_Provider].fld_ProviderID
END;