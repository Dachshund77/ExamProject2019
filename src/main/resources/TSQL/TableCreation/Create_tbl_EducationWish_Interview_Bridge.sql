CREATE TABLE tbl_EducationWish_Interview_Bridge
(
    fld_EducationWishID INT NOT NULL,
    FOREIGN KEY (fld_EducationWishID) REFERENCES tbl_EducationWish (fld_EducationWishID) ON DELETE CASCADE,
    fld_InterviewID     INT NOT NULL,
    FOREIGN KEY (fld_InterviewID) REFERENCES tbl_Interview (fld_InterviewID) ON DELETE CASCADE
);