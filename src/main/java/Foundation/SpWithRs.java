package Foundation;


/**
 * Enum class that hold the actual name of the stored procedure that return with a RS.
 * Prevents spelling mistakes and makes migration of names easier.
 */
public enum SpWithRs implements Procedure {

    //FIND
    FIND_PROVIDER("sp_Find_Provider"),

    //PlACE


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