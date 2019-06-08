CREATE OR ALTER PROCEDURE sp_Find_All_Display_Consultations(@ConsultationID INT, @ConsultationName NVARCHAR(50),
                                                            @ConsultationMinDate DATE, @ConsultationMaxDate DATE)
AS
BEGIN
    SET NOCOUNT ON
    SELECT fld_ConsultationID AS ConsultationID,
           fld_ConsultationName AS ConsultationName,
           fld_StartDate AS ConsultationStartDate,
           fld_EndDate AS ConsultationEndDate
    FROM udf_Filter_tbl_Consultation(@ConsultationID, @ConsultationName, @ConsultationMinDate, @ConsultationMaxDate)
END;