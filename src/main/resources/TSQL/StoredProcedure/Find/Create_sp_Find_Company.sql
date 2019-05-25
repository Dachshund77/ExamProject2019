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
        @tbl_Consultation_Employee_Bridge AS TableType_Consultation_Employee_Bridge
    INSERT INTO @tbl_Consultation_Employee_Bridge
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

    SELECT [@tbl_Company].fld_CompanyID                           AS tbl_Company_PK_fld_CompanyID,                     --Company
           [@tbl_Company].fld_CvrNr                               AS tbl_Company_fld_CvrNr,
           [@tbl_Company].fld_CompanyName                         AS tbl_Company_fld_CompanyName,
           [@tbl_Company_Education_Bridge].fld_CompanyID          AS tbl_Company_Education_Bridge_FK_fld_CompanyID,    -- Company education bridge
           [@tbl_Company_Education_Bridge].fld_AmuNR              AS tbl_Company_Education_Bridge_FK_fld_AmurNr,
           [@tbl_Consultation].fld_ConsultationID                 AS tbl_Consultation_PK_fld_ConsultationID,           --Consultation
           [@tbl_Consultation].fld_ConsultationName               AS tbl_Consultation_fld_ConsultationName,
           [@tbl_Consultation].fld_StartDate                      AS tbl_Consultation_fld_StartDate,
           [@tbl_Consultation].fld_EndDate                        AS tbl_Consultation_fld_EndDate,
           [@tbl_Consultation].fld_CompanyID                      AS tbl_Consultation_FK_fld_CompanyID,
           [@tbl_Consultation_Employee_Bridge].fld_ConsultationID AS tbl_Consultation_Employee_Bridge_FK_fld_ConsultationID,
           [@tbl_Consultation_Employee_Bridge].fld_EmployeeID     AS tbl_Consultation_Employee_Bridge_FK_fld_EmployeeID,
           [@tbl_Employee].fld_EmployeeID                         AS tbl_Employee_PK_fld_EmployeeID,                   --employee
           [@tbl_Employee].fld_EmployeeFirstName                  AS tbl_Employee_fld_EmployeeFirstName,
           [@tbl_Employee].fld_EmployeeLastName                   AS tbl_Employee_fld_EmployeeLastName,
           [@tbl_Employee].fld_CprNr                              AS tbl_Employee_fld_CprNr,
           [@tbl_Employee].fld_Email                              AS tbl_Employee_fld_Email,
           [@tbl_Employee].fld_PhoneNr                            AS tbl_Employee_fld_PhoneNr,
           [@tbl_Interview].fld_InterviewID                       AS tbl_Interview_PK_fld_InterviewID,                 --Interview
           [@tbl_Interview].fld_InterviewName                     AS tbl_Interview_fld_InterviewName,
           [@tbl_Interview].fld_Employee_ID                       AS tbl_Interview_FK_fld_EmployeeID,
           [@tbl_Interview].fld_ProductUnderstanding              AS tbl_Interview_fld_ProductUnderstanding,
           [@tbl_Interview].fld_ProblemUnderstanding              AS tbl_Interview_fld_ProblemUnderstanding,
           [@tbl_Interview].fld_Flexibility                       AS tbl_Interview_fld_Flexibility,
           [@tbl_Interview].fld_QualityAwareness                  AS tbl_Interview_fld_QualityAwarness,
           [@tbl_Interview].fld_Cooperation                       AS tbl_Interview_fld_Cooperation,
           [@tbl_EducationWish].fld_InterviewID                   AS tbl_EducationWish_PK_fld_EducationWishID,         --education wish
           [@tbl_EducationWish].fld_AmuNR                         AS tbl_EducationWish_FK_fld_AmuNr,
           [@tbl_EducationWish].fld_InterviewID                   AS tbl_EducationWish_FK_fld_InterviewID,
           [@tbl_EducationWish].fld_WishPriority                  AS tbl_EducationWish_fld_WishPriority,
           [@tbl_FinishedEducation].fld_FinishedEducationID       AS tbl_FinishedEducation_PK_fld_FinishedEducationID, --finished education
           [@tbl_FinishedEducation].fld_AmuNR                     AS tbl_FinishedEducation_FK_fld_AmuNr,
           [@tbl_FinishedEducation].fld_InterviewID               AS tbl_FinishedEducation_FK_fld_InterviewID,
           [@tbl_FinishedEducation].fld_FinishedDate              AS tbl_FinishedEducation_fld_FinishedDate,
           [edu1].fld_AmuNR                                       AS tbl_Education_PK_fld_AmuNr,                       --Education
           [edu1].fld_EducationName                               AS tbl_Education_fld_EducationName,
           [edu1].fld_Description                                 AS tbl_Education_fld_Description,
           [edu1].fld_NoOfDays                                    AS tbl_Education_fld_NoOfDays,
           [edu1].fld_ProviderID                                  AS tbl_Education_FK_fld_ProviderID,
           [@tbl_Date].fld_DateID                                 AS tbl_Date_PK_fld_DateID,                           -- Date of educations
           [@tbl_Date].fld_Date                                   AS tbl_Date_fld_Date,
           [@tbl_Date].fld_AmuNr                                  AS tbl_Date_FK_fld_AmuNr,
           [@tbl_Provider].fld_ProviderID                         AS tbl_Provider_PK_fld_ProviderID,                   -- Provider
           [@tbl_Provider].fld_ProviderName                       AS tbl_Provider_fld_ProviderName

           --Bridges?

    FROM @tbl_Company
             INNER JOIN @tbl_Consultation
                        ON [@tbl_Company].fld_CompanyID = [@tbl_Consultation].fld_CompanyID
             INNER JOIN @tbl_Consultation_Employee_Bridge ON [@tbl_Consultation].fld_ConsultationID =
                                                             [@tbl_Consultation_Employee_Bridge].fld_ConsultationID
             INNER JOIN @tbl_Employee
                        ON [@tbl_Consultation_Employee_Bridge].fld_EmployeeID = [@tbl_Employee].fld_EmployeeID
             INNER JOIN @tbl_Interview ON [@tbl_Employee].fld_EmployeeID = [@tbl_Interview].fld_Employee_ID
             INNER JOIN @tbl_EducationWish ON [@tbl_Interview].fld_InterviewID = [@tbl_EducationWish].fld_InterviewID
             INNER JOIN @tbl_FinishedEducation
                        ON [@tbl_Interview].fld_InterviewID = [@tbl_FinishedEducation].fld_InterviewID
             INNER JOIN @tbl_Education AS edu1 ON [@tbl_FinishedEducation].fld_AmuNR = [edu1].fld_AmuNR
             INNER JOIN @tbl_Education AS edu2 ON [@tbl_EducationWish].fld_AmuNR = [edu2].fld_AmuNR
             INNER JOIN @tbl_Date ON [edu1].fld_AmuNR = [@tbl_Date].fld_AmuNr
             INNER JOIN @tbl_Provider ON [edu1].fld_ProviderID = [@tbl_Provider].fld_ProviderID
             INNER JOIN @tbl_Company_Education_Bridge
                        ON [@tbl_Company].fld_CompanyID = [@tbl_Company_Education_Bridge].fld_CompanyID
             INNER JOIN @tbl_Education ON [@tbl_Company_Education_Bridge].fld_AmuNR = [@tbl_Education].fld_AmuNR

END;