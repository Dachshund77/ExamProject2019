USE db_SmartAcademy;
CREATE TABLE tbl_Company_Education_Bridge
(
    fld_CompanyID           INT NOT NULL,
    FOREIGN KEY (fld_CompanyID) REFERENCES tbl_Company (fld_CompanyID) ON DELETE CASCADE ON UPDATE CASCADE ,
    fld_AmuNR INT NOT NULL,
    FOREIGN KEY (fld_AmuNR) REFERENCES tbl_Education (fld_AmuNR) ON DELETE CASCADE
);