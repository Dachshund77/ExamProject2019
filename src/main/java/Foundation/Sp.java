package Foundation;

import java.util.EnumSet;

/**
 * Enum class that hold the actual name of the stored procedure that return no RS.
 * Prevents spelling mistakes and makes migration of names easier.
 */
public enum Sp implements Procedure {
    //DELETE
    DELETE_CONSULTATION_BY_COMPANY_ID("sp_Delete_Consultation_By_Company_ID"),
    DELETE_DATE_BY_AMU_NR("sp_Delete_Date_By_AmuNr"),
    DELETE_EMPLOYEE_BY_CONSULTATION_ID("sp_Delete_Employee_By_Consultation_ID"),
    DELETE_FINISHED_EDUCATION_BY_INTERVIEW_ID("sp_Delete_Finished_Education_By_Interview_ID"),
    DELETE_INTERVIEW_BY_EMPLOYEE_ID("sp_Delete_Interview_By_Employee_ID"),
    DELETE_EDUCATION_WISH_BY_INTERVIEW_ID("sp_Delete_Education_Wish_By_Interview_ID"),
    DELETE_CONSULTATION_EMPLOYEE_BRIDGE_BY_CONSULTATION_ID("sp_Delete_Consultation_Employee_Bridge_By_Consultation_ID"),
    DELETE_COMPANY_BY_PK("sp_Delete_Company_By_PK"),
    DELETE_CONSULTATION_BY_PK("sp_Delete_Consultation_By_PK"),
    DELETE_EDUCATION_BY_PK("sp_Delete_Education_By_PK"),
    DELETE_EMPLOYEE_BY_PK("sp_Delete_Employee_By_PK"),
    DELETE_INTERVIEW_BY_PK("sp_Delete_Interview_By_PK"),
    DELETE_PROVIDER_BY_PK("sp_Delete_Provider_By_PK"),

    //DELETE ALL
    DELETE_ALL_PROVIDERS("sp_Delete_All_Providers"),
    DELETE_ALL_EDUCATIONS("sp_Delete_All_Educations"),
    DELETE_ALL_EDUCATION_WISHES("sp_Delete_All_Education_Wishes"),
    DELETE_ALL_FINISHED_EDUCATIONS("sp_Delete_All_Finished_Educations"),
    DELETE_ALL_INTERVIEWS("sp_Delete_All_Interviews"),
    DELETE_ALL_EMPLOYEES("sp_Delete_All_Employees"),
    DELETE_ALL_CONSULTATIONS("sp_Delete_All_Consultations"),
    DELETE_ALL_COMPANIES("sp_Delete_All_Companies"),
    DELETE_ALL_DB_ENTRIES("sp_Delete_All_DB_Entries"),


    //INSERT
    INSERT_CONSULTATION_EMPLOYEE_BRIDGE("sp_Insert_Consultation_Employee_Bridge"),

    //PLACE
    PLACE_DATE("sp_Place_Date"),
    PLACE_EDUCATION_FROM_COMPANY("sp_Place_Education_From_Company"),
    PLACE_EMPLOYEE_FROM_CONSULTATION("sp_Place_Employee_From_Consultation");


    private final String NAME;

    Sp(String NAME) {
        this.NAME = NAME;
    }

    /**
     * Gets the actual name of the Stored procedure as a String.
     *
     * @return String of sp name
     */
    @Override
    public String get() {
        return NAME;
    }

    static void test(){
        EnumSet<Sp> all = EnumSet.allOf(Sp.class); //TODO remove but very usefull
    }
}
