CREATE OR ALTER PROCEDURE sp_Find_Employee(@fld_EmployeeID INT, @fld_EmployeeFirstName VARCHAR(30),
                                           @fld_EmployeeLastName VARCHAR(30), @fld_CprNr VARCHAR(10),
                                           @fld_Email VARCHAR(30), @fld_PhoneNr VARCHAR(20), @fld_InterviewID INT,
                                           @fld_InterviewName VARCHAR(30), @AmuNr INT,
                                           @EducationName VARCHAR(30), @NoOfDays INT, @DateID INT,
                                           @DateMinDate DATE, @DateMaxDate DATE, @ProviderID INT,
                                           @ProviderName VARCHAR(30))
AS
BEGIN
    DECLARE
        @tbl_Employee AS TableType_Employee
    INSERT INTO @tbl_Employee
    SELECT fld_EmployeeID,
           fld_EmployeeFirstName,
           fld_EmployeeLastName,
           fld_CprNr,
           fld_Email,
           fld_PhoneNr
    FROM udf_Filter_tbl_Employee(@fld_EmployeeID, @fld_EmployeeFirstName, @fld_EmployeeLastName, @fld_CprNr, @fld_Email,
                                 @fld_PhoneNr)

    DECLARE
        @tbl_Interview AS TableType_Interview
    INSERT INTO @tbl_Interview
    SELECT fld_InterviewID,
           fld_InterviewName,
           fld_Employee_ID,
           fld_ProductUnderstanding,
           fld_ProblemUnderstanding,
           fld_Flexibility,
           fld_QualityAwareness,
           fld_Cooperation
    FROM udf_Filter_tbl_Interview(@fld_InterviewID, @fld_InterviewName)

    DECLARE
        @tbl_EducationWish AS TableType_EducationWish
    INSERT INTO @tbl_EducationWish
    SELECT fld_EducationWishID, fld_AmuNR, fld_InterviewID, fld_WishPriority
    FROM tbl_EducationWish

    DECLARE
        @tbl_FinishedEducation AS TableType_FinishedEducation
    INSERT INTO @tbl_FinishedEducation
    SELECT fld_FinishedEducationID, fld_AmuNR, fld_InterviewID, fld_FinishedDate
    FROM tbl_FinishedEducation

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

    SELECT [@tbl_Employee].fld_EmployeeID,                  --employee
           [@tbl_Employee].fld_EmployeeFirstName,
           [@tbl_Employee].fld_EmployeeLastName,
           [@tbl_Employee].fld_CprNr,
           [@tbl_Employee].fld_Email,
           [@tbl_Employee].fld_PhoneNr,
           [@tbl_Interview].fld_InterviewID,                 --Interview
           [@tbl_Interview].fld_InterviewName,
           [@tbl_Interview].fld_ProductUnderstanding,
           [@tbl_Interview].fld_ProblemUnderstanding,
           [@tbl_Interview].fld_Flexibility,
           [@tbl_Interview].fld_QualityAwareness,
           [@tbl_Interview].fld_Cooperation,
           [@tbl_EducationWish].fld_InterviewID,             --education wish
           [@tbl_EducationWish].fld_WishPriority,
           [@tbl_FinishedEducation].fld_FinishedEducationID, --finished education
           [@tbl_FinishedEducation].fld_FinishedDate,
           [edu1].fld_AmuNR,                                 --Education
           [edu1].fld_EducationName,
           [edu1].fld_Description,
           [edu1].fld_NoOfDays,
           [@tbl_Date].fld_Date,                             -- Date of educations
           [@tbl_Provider].fld_ProviderID,                   -- Provider
           [@tbl_Provider].fld_ProviderName


    FROM @tbl_Employee
             INNER JOIN @tbl_Interview ON [@tbl_Employee].fld_EmployeeID = [@tbl_Interview].fld_Employee_ID
             INNER JOIN @tbl_EducationWish ON [@tbl_Interview].fld_InterviewID = [@tbl_EducationWish].fld_InterviewID
             INNER JOIN @tbl_FinishedEducation
                        ON [@tbl_Interview].fld_InterviewID = [@tbl_FinishedEducation].fld_InterviewID
             INNER JOIN @tbl_Education AS edu1 ON [@tbl_FinishedEducation].fld_AmuNR = [edu1].fld_AmuNR
             INNER JOIN @tbl_Education AS edu2 ON [@tbl_EducationWish].fld_AmuNR = [edu2].fld_AmuNR
             INNER JOIN @tbl_Date ON [edu1].fld_AmuNR = [@tbl_Date].fld_AmuNr
             INNER JOIN @tbl_Provider ON [edu1].fld_ProviderID = [@tbl_Provider].fld_ProviderID
END;