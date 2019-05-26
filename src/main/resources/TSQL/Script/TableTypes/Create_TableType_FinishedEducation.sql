CREATE TYPE TableType_FinishedEducation AS TABLE
(
    fld_FinishedEducationID INT PRIMARY KEY,
    fld_AmuNR               INT NOT NULL,
    fld_InterviewID         INT NOT NULL,
    fld_FinishedDate        DATE
);