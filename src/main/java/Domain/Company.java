package Domain;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class Company implements SQLData {

    private SimpleIntegerProperty companyID;
    private SimpleStringProperty cvrNr;
    private SimpleStringProperty companyName;
    private ArrayList<Consultation> consultations;
    private EducationList educationList;

    private String sql_type;

    public Company(Integer companyID, String cvrNr, String companyName /*ArrayList<Consultation> consultations, EducationList educationList*/) {
        this.companyID = new SimpleIntegerProperty(companyID);
        this.cvrNr = new SimpleStringProperty(cvrNr);
        this.companyName = new SimpleStringProperty(companyName);
        //this.consultations = consultations;
        //this.educationList = educationList;
    }


    @Override
    public String getSQLTypeName() throws SQLException {
        return sql_type;
    }

    @Override
    public void readSQL(SQLInput stream, String typeName) throws SQLException {
        sql_type = typeName;
        companyID = new SimpleIntegerProperty(stream.readInt());
        cvrNr = new SimpleStringProperty(stream.readString());
        companyName = new SimpleStringProperty(stream.readString());
        //TODO i doubt this works here to create a educationList?
    }

    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        stream.writeInt(companyID.get());
        stream.writeString(cvrNr.get());
        stream.writeString(companyName.get());
        //TODO i doubt this works here to create a educationList?
    }
}
