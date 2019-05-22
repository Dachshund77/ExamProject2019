USE db_SmartAcademy;
CREATE PROCEDURE sp_InsertEducationWish_Interview_Bridge(@EducationWishId INT, @InterviewID INT)
AS
BEGIN
    INSERT INTO tbl_EducationWish_Interview_Bridge
    VALUES (@EducationWishId, @InterviewID)
END;