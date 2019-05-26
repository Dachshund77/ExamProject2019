CREATE TYPE TableType_Date AS TABLE
(
    fld_DateID INT PRIMARY KEY,
    fld_AmuNr  INT  NOT NULL,
    fld_Date   DATE NOT NULL
);