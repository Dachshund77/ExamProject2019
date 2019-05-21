package Domain;

import Application.Controllers;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import sun.reflect.Reflection;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.util.ArrayList;

import java.lang.reflect.*;

public class Company implements SQLData {

    private SimpleIntegerProperty companyID;
    private SimpleStringProperty cvrNr;
    private SimpleStringProperty companyName;
    private ArrayList<Consultation> consultations;
    private EducationList educationList;

    public Company(Integer companyID, String cvrNr, String companyName, ArrayList<Consultation> consultations, EducationList educationList) {
        this.companyID = new SimpleIntegerProperty(companyID);
        this.cvrNr = new SimpleStringProperty(cvrNr);
        this.companyName = new SimpleStringProperty(companyName);
        this.consultations = consultations;
        this.educationList = educationList;
    }


    @Override
    public String getSQLTypeName() throws SQLException {
        return null;
    }

    @Override
    public void readSQL(SQLInput stream, String typeName) throws SQLException {

    }

    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {

    }
}
