package Foundation;


/**
 * Enum class that hold the actual name of the stored procedure that return a single ID.
 * Prevents spelling mistakes and makes migration of names easier.
 */
public enum SpGetKey implements Procedure {

    PLACE_PROVIDER("sp_Place_Provider"),
    PLACE_FINISHED_EDUCATION("sp_Place_Finished_Education"),
    PLACE_EDUCATION_WISH("sp_Place_Education_Wish"),
    PLACE_INTERVIEW("sp_Place_Interview"),
    PLACE_EMPLOYEE("sp_Place_Employee"),
    PLACE_COMPANY("sp_Place_Company"),
    PLACE_CONSULTATION("sp_Place_Consultation"),
    PLACE_EDUCATION("sp_Place_Education");

    private final String NAME;

    SpGetKey(String NAME) {
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