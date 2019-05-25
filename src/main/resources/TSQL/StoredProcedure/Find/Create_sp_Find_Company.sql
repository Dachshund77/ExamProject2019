CREATE OR ALTER PROCEDURE sp_Find_Company(@CompanyID INT, @CvrNr VARCHAR(80),
                                          @CompanyName VARCHAR(50), @ConsultationID INT, @ConsultationName VARCHAR(50),
                                          @ConsultationMinDate DATE, @ConsultationMaxDate DATE,
                                          @fld_EmployeeID INT, @fld_EmployeeFirstName VARCHAR(30),
                                          @fld_EmployeeLastName VARCHAR(30), @fld_CprNr VARCHAR(10),
                                          @fld_Email VARCHAR(30), @fld_PhoneNr VARCHAR(20), @fld_InterviewID INT,
                                          @fld_InterviewName VARCHAR(30), @AmuNr INT,
                                          @EducationName VARCHAR(30), @NoOfDays INT, @DateID INT,
                                          @DateMinDate DATE, @DateMaxDate DATE, @ProviderID INT,
                                          @ProviderName VARCHAR(30))
AS
BEGIN
    DECLARE
        @tbl_Company AS TableType_Company
    INSERT INTO @tbl_Company
    SELECT fld_CompanyID,
           fld_CvrNr,
           fld_CompanyName
    FROM udf_Filter_Company(@CompanyID, @CvrNr, @CompanyName)

    DECLARE
        @tbl_Company_Education_Bridge AS TableType_Company_Education_Bridge
    INSERT INTO @tbl_Company_Education_Bridge
    SELECT fld_CompanyID,
           fld_AmuNR
    FROM tbl_Company_Education_Bridge

    DECLARE
        @tbl_Consultation AS TableType_Consultation
    INSERT INTO @tbl_Consultation
    SELECT fld_ConsultationID,
           fld_ConsultationName,
           fld_StartDate,
           fld_EndDate,
           fld_CompanyID
    FROM udf_Filter_tbl_Consultation(@ConsultationID, @ConsultationName, @ConsultationMinDate, @ConsultationMaxDate)

    DECLARE
        @tbl_Consultation_EmployeeBridge AS TableType_Consultation_EmployeeBridge
    INSERT INTO @tbl_Consultation_EmployeeBridge
    SELECT fld_ConsultationID,
           fld_EmployeeID
    FROM tbl_Consultation_Employee_Bridge

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

    SELECT [@tbl_Company].fld_CompanyID,                     --Company
           [@tbl_Company].fld_CvrNr,
           [@tbl_Company].fld_CompanyName,
           [@tbl_Consultation].fld_ConsultationID,           --Consultation
           [@tbl_Consultation].fld_ConsultationName,
           [@tbl_Consultation].fld_StartDate,
           [@tbl_Consultation].fld_EndDate,
           [@tbl_Employee].fld_EmployeeID,                   --employee
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

    FROM @tbl_Company

             INNER JOIN @tbl_Consultation ON [@tbl_Company].fld_CompanyID = [@tbl_Consultation].fld_CompanyID
             INNER JOIN @tbl_Consultation_EmployeeBridge ON [@tbl_Consultation].fld_ConsultationID = [@tbl_Consultation_EmployeeBridge].fld_ConsultationID
             INNER JOIN @tbl_Employee ON [@tbl_Consultation_EmployeeBridge].fld_EmployeeID = [@tbl_Employee].fld_EmployeeID
             INNER JOIN @tbl_Interview ON [@tbl_Employee].fld_EmployeeID = [@tbl_Interview].fld_Employee_ID
             INNER JOIN @tbl_EducationWish ON [@tbl_Interview].fld_InterviewID = [@tbl_EducationWish].fld_InterviewID
             INNER JOIN @tbl_FinishedEducation ON [@tbl_Interview].fld_InterviewID = [@tbl_FinishedEducation].fld_InterviewID
             INNER JOIN @tbl_Education AS edu1 ON [@tbl_FinishedEducation].fld_AmuNR = [edu1].fld_AmuNR
             INNER JOIN @tbl_Education AS edu2 ON [@tbl_EducationWish].fld_AmuNR = [edu2].fld_AmuNR
             INNER JOIN @tbl_Date ON [edu1].fld_AmuNR = [@tbl_Date].fld_AmuNr
             INNER JOIN @tbl_Provider ON [edu1].fld_ProviderID = [@tbl_Provider].fld_ProviderID
             INNER JOIN @tbl_Company_Education_Bridge ON [@tbl_Company].fld_CompanyID = [@tbl_Company_Education_Bridge].fld_CompanyID
             INNER JOIN @tbl_Education ON [@tbl_Company_Education_Bridge].fld_AmuNR = [@tbl_Education].fld_AmuNR

END;