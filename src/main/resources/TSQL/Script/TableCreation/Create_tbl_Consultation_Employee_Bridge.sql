USE db_SmartAcademy;
CREATE TABLE tbl_Consultation_Employee_Bridge
(
    fld_ConsultationID INT NOT NULL,
    FOREIGN KEY (fld_ConsultationID) REFERENCES tbl_Consultation (fld_ConsultationID) ON DELETE CASCADE ON UPDATE CASCADE ,
    fld_EmployeeID     INT NOT NULL,
    FOREIGN KEY (fld_EmployeeID) REFERENCES tbl_Employee (fld_EmployeeID) ON DELETE CASCADE
);