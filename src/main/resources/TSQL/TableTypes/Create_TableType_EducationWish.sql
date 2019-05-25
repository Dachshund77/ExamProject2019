CREATE TYPE TableType_EducationWish AS TABLE
(
    fld_EducationWishID INT PRIMARY KEY,
    fld_AmuNR           INT NOT NULL,
    fld_InterviewID     INT NOT NULL,
    fld_WishPriority    INT NOT NULL
)