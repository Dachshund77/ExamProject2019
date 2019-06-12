package Foundation;


/**
 * Enum class that hold the actual name of the stored procedure that return with a RS.
 * Prevents spelling mistakes and makes migration of names easier.
 */
public enum SpWithRs implements Procedure {

    //FIND by ID
    FIND_PROVIDER_BY_ID("sp_Find_Provider_By_ID"),
    FIND_EDUCATION_BY_ID("sp_Find_Education_By_ID"),
    FIND_COMPANY_BY_ID("sp_Find_Company_By_ID"),
    FIND_CONSULTATION_BY_ID("sp_Find_Consultation_By_ID"),
    FIND_EMPLOYEE_BY_ID("sp_Find_Employee_By_ID"),
    FIND_INTERVIEW_BY_ID("sp_Find_Interview_By_ID"),

    //FIND BY FK
    FIND_CONSULTATIONS_BY_COMPANY_ID("sp_Find_Consultations_By_Company_ID"),
    FIND_DATES_BY_EDUCATION_ID("sp_Find_Dates_By_Education_ID"),
    FIND_EDUCATIONS_BY_EDUCATION_WISH_ID("sp_Find_Educations_By_Education_Wish_ID"),
    FIND_EDUCATION_WISHES_BY_INTERVIEW_ID("sp_Find_Education_Wishes_By_Interview_ID"),
    FIND_EDUCATIONS_BY_FINISHED_EDUCATION_ID("sp_Find_Educations_By_Finished_Education_ID"),
    FIND_EMPLOYEES_BY_CONSULTATION_ID("sp_Find_Employees_By_Consultation_ID"),
    FIND_FINISHED_EDUCATIONS_BY_INTERVIEW_ID("sp_Finished_Educations_By_Interview_ID"),
    FIND_INTERVIEWS_BY_EMPLOYEE_ID("sp_Find_Interviews_By_Employee_ID"),
    FIND_PROVIDER_BY_EDUCATION_ID("sp_Find_Provider_By_Education_ID"),
    FIND_EMPLOYEE_BY_INTERVIEW_ID("sp_Find_Employee_By_Interview_ID"),
    FIND_COMPANY_BY_CONSULTATION_ID("sp_Find_Company_By_Consultation_ID"),

    //Find Display by search
    FIND_DISPLAY_COMPANIES("sp_Find_Display_Company"),
    FIND_DISPLAY_CONSULTATIONS("sp_Find_Display_Consultations"),
    FIND_DISPLAY_EDUCATIONS("sp_Find_Display_Educations"),
    FIND_DISPLAY_EMPLOYEES("sp_Find_Display_Employees"),
    FIND_DISPLAY_INTERVIEWS("sp_Find_Display_Interviews"),
    FIND_DISPLAY_PROVIDERS("sp_Find_Display_Providers"),

    //FIND ALL DISPLAY
    FIND_ALL_DISPLAY_COMPANIES("sp_Find_All_Display_Companies"),
    FIND_ALL_DISPLAY_CONSULTATIONS("sp_Find_All_Display_Consultations"),
    FIND_ALL_DISPLAY_EDUCATIONS("sp_Find_All_Display_Educations"),
    FIND_ALL_DISPLAY_EMPLOYEES("sp_Find_All_Display_Employees"),
    FIND_ALL_DISPLAY_INTERVIEWS("sp_Find_All_Display_Interviews"),
    FIND_ALL_DISPLAY_PROVIDERS("sp_Find_All_Display_Providers"),

    //GET
    GET_PROCEDURE_META_DATA("sp_Get_SP_Meta_Data");

    private final String NAME;

    SpWithRs(String NAME) {
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