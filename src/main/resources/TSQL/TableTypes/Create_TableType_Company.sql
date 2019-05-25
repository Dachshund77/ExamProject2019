CREATE TYPE TableType_Company AS TABLE
(
    fld_CompanyID   INT PRIMARY KEY,
    fld_CvrNr       VARCHAR(80) NOT NULL,
    fld_CompanyName VARCHAR(50) NOT NULL
);