USE db_SmartAcademy;
CREATE PROCEDURE sp_InsertFinishedEducation_Interview_Bridge(@FinishedEducationID INT, @InterviewID INT)
AS
BEGIN
    INSERT INTO tbl_FinishedEducation_Interview_Bridge
    VALUES (@FinishedEducationID, @InterviewID)
END;