USE master;
IF EXISTS(SELECT *
          FROM sys.databases
          WHERE name = 'db_SmartAcademy')
    BEGIN
        DROP DATABASE db_SmartAcademy
    END
CREATE DATABASE db_SmartAcademy;