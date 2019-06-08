CREATE OR ALTER PROCEDURE sp_Find_Display_Employees(@CompanyID INT, @CvrNr VARCHAR(80),
                                                   @CompanyName VARCHAR(50), @ConsultationID INT, @ConsultationName VARCHAR(50),
                                                   @ConsultationMinDate DATE, @ConsultationMaxDate DATE,
                                                   @fld_EmployeeID INT, @fld_EmployeeFirstName VARCHAR(30),
                                                   @fld_EmployeeLastName VARCHAR(30), @fld_CprNr VARCHAR(10),
                                                   @fld_Email VARCHAR(30), @fld_PhoneNr VARCHAR(20), @fld_InterviewID INT,
                                                   @fld_InterviewName VARCHAR(30), @AmuNr INT,
                                                   @EducationName VARCHAR(30), @NoOfDays INT,
                                                   @DateMinDate DATE, @DateMaxDate DATE, @ProviderID INT,
                                                   @ProviderName VARCHAR(30))
AS
BEGIN
    SET NOCOUNT ON

    DECLARE
        @tbl_Company AS TableType_Company
    INSERT INTO @tbl_Company
    SELECT fld_CompanyID,
           fld_CvrNr,
           fld_CompanyName
    FROM udf_Filter_tbl_Company(@CompanyID, @CvrNr, @CompanyName)

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
    FROM udf_Filter_tbl_Date(@DateMinDate, @DateMaxDate)

    DECLARE
        @tbl_Provider AS TableType_Provider
    INSERT INTO @tbl_Provider
    SELECT fld_ProviderID, fld_ProviderName
    FROM udf_Filter_tbl_Provider(@ProviderID, @ProviderName)

    SELECT * FROM @tbl_Company
                      INNER JOIN @tbl_Consultation ON [@tbl_Company].fld_CompanyID = [@tbl_Consultation].fld_CompanyID
                      INNER JOIN @tbl_Consultation_Employee_Bridge ON [@tbl_Consultation].fld_ConsultationID = [@tbl_Consultation_Employee_Bridge].fld_ConsultationID
                      INNER JOIN @tbl_Employee ON [@tbl_Consultation_Employee_Bridge].fld_EmployeeID = [@tbl_Employee].fld_EmployeeID
                      INNER JOIN @tbl_Interview ON [@tbl_Employee].fld_EmployeeID = [@tbl_Interview].fld_Employee_ID
                      INNER JOIN @tbl_EducationWish ON [@tbl_Interview].fld_InterviewID = [@tbl_EducationWish].fld_InterviewID
                      INNER JOIN @tbl_FinishedEducation ON [@tbl_Interview].fld_InterviewID = [@tbl_FinishedEducation].fld_InterviewID

                      INNER JOIN @tbl_Education edu1 ON [@tbl_EducationWish].fld_AmuNR = [edu1].fld_AmuNR
                      INNER JOIN @tbl_Education edu2 ON [@tbl_FinishedEducation].fld_AmuNR = [edu2].fld_AmuNR

                      INNER JOIN @tbl_Provider prov1 ON [edu1].fld_ProviderID = [prov1].fld_ProviderID
                      INNER JOIN @tbl_Provider prov2 ON [edu2].fld_ProviderID = [prov2].fld_ProviderID

                      INNER JOIN @tbl_Date date1 ON [edu1].fld_AmuNR = [date1].fld_AmuNr
                      INNER JOIN @tbl_Date date2 ON [edu1].fld_AmuNR = [date2].fld_AmuNr
END;