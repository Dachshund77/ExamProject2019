CREATE TABLE tbl_EducationWish
(
    fld_EducationWishID INT IDENTITY (1,1) PRIMARY KEY,
    fld_AmuNR           INT NOT NULL,
    FOREIGN KEY (fld_AmuNR) REFERENCES tbl_Education (fld_AmuNR),
    fld_WishPriority    INT NOT NULL
);