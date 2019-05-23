USE db_SmartAcademy;
CREATE PROCEDURE sp_DeleteEmployeeByConsultationID(@ConsultationID INT)
AS
BEGIN
    DELETE
    FROM tbl_Employee
    WHERE fld_EmployeeID IN (
        SELECT fld_EmployeeID FROM tbl_Consultation_Employee_Bridge WHERE fld_ConsultationID = @ConsultationID)

END;