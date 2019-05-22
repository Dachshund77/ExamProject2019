USE db_SmartAcademy;
CREATE PROCEDURE sp_InsertConsultation_Employee_Bridge(@ConsultationID INT, @EmployeeID INT)
AS
BEGIN
    INSERT INTO tbl_Consultation_Employee_bridge VALUES (@ConsultationID, @EmployeeID)
END;