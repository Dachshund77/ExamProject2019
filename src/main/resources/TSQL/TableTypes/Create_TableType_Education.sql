CREATE TYPE TableType_Education AS TABLE
(
    fld_AmuNR         Int PRIMARY KEY,
    fld_ProviderID    int         NOT NULL,
    fld_EducationName VARCHAR(30) NOT NULL,
    fld_Description   NVARCHAR(MAX),
    fld_NoOfDays      INT         NOT NULL
)