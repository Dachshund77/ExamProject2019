CREATE TYPE TableType_Consultation AS TABLE
(
    fld_ConsultationID   INT PRIMARY KEY,
    fld_ConsultationName VARCHAR(50) NOT NULL,
    fld_StartDate        DATE        NOT NULL,
    fld_EndDate          DATE        NOT NULL,
    fld_CompanyID        INT         NOT NULL,
)