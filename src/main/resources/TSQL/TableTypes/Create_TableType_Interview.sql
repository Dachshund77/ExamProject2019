CREATE TYPE TableType_Interview AS TABLE
(
    fld_InterviewID          int PRIMARY KEY,
    fld_InterviewName        VARCHAR(30) NOT NULL,
    fld_Employee_ID          INT         NOT NULL,
    fld_ProductUnderstanding INT,
    fld_ProblemUnderstanding INT,
    fld_Flexibility          INT,
    fld_QualityAwareness     INT,
    fld_Cooperation          INT,
)