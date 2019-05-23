package Foundation;

/**
 * Enum class that hold the actual name of the stored procedure that return no RS.
 * Prevents spelling mistakes and makes migration of names easier.
 */
public enum Sp implements Procedure {
    //DELETE
    DELETE_COMPANY_EDUCATION_BRIDGE("sp_DeleteCompany_EducationBridge"),
    DELETE_CONSULTATION_BY_COMPANY_ID("sp_DeleteConsultationByCompanyID"),
    DELETE_DATE_BY_AMU_NR("sp_DeleteDateByAmuNr"),
    DELETE_EMPLOYEE_BY_CONSULTATION_ID("sp_DeleteEmployeeByConsultationID"),
    DELETE_FINISHED_EDUCATION_BY_INTERVIEW_ID("sp_DeleteFinishedEducationByInterviewID"),
    DELETE_EDUCATION_WISH_BY_INTERVIEW_ID("sp_DeleteEducationWishByInterviewID"),

    //INSERT
    INSERT_COMPANY_EDUCATION_BRIDGE("sp_InsertCompany_Education_Bridge"),
    INSERT_CONSULTATION_EMPLOYEE_BRIDGE("sp_InsertConsultation_Employee_Bridge"),

    //PLACE //TODO need to change names for this
    PLACE_CONSULTATION("sp_InsertConsultation"),
    PLACE_DATE("sp_InsertDate"),
    PLACE_EDUCATION_FROM_COMPANY("sp_InsertEducationAsCompany"),
    PLACE_EDUCATION_WISH("sp_InsertEducationWish"),
    PLACE_EMPLOYEE("sp_InsertEmployee"),
    PLACE_EMPLOYEE_FROM_CONSULTATION("sp_InsertEmployeeAsConsultation"),
    PLACE_FINISHED_EDUCATION("sp_InsertFinishedEducation"),
    PLACE_PROVIDER("sp_InsertProvider"),
    PLACE_INTERVIEW("sp_InsertInterview"),
    PLACE_COMPANY("sp_InsertCompany");

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
