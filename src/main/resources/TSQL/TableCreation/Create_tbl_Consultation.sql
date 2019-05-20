CREATE TABLE tbl_Consultation
  (
      fld_ConsultationID   INT IDENTITY (1,1) PRIMARY KEY,
      fld_ConsultationName VARCHAR(50) NOT NULL,
      fld_StartDate        DATE        NOT NULL,
      fld_EndDate          DATE        NOT NULL,
      fld_CompanyID        INT         NOT NULL,
      FOREIGN KEY (fld_CompanyID) REFERENCES tbl_Company (fld_CompanyID)
  );