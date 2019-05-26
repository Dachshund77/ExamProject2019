package Foundation;

/**
 * Enum class that hold the actual name of the stored procedure that return no RS.
 * Prevents spelling mistakes and makes migration of names easier.
 */
public enum Sp implements Procedure {
    //DELETE
    DELETE_COMPANY_EDUCATION_BRIDGE_BY_COMPANY_ID("sp_Delete_Company_Education_Bridge_By_Company_ID"),
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

    //INSERT
    INSERT_COMPANY_EDUCATION_BRIDGE("sp_Insert_Company_Education_Bridge"),
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
}
