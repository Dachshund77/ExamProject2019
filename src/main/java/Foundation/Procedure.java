package Foundation;

/**
 * Interface for Enum type that will stored Procedure information.
 * This interface makes sure that implementing classes need to define its name.
 */
public interface Procedure {

    /**
     * Contract that enum type will return its actual stored procedure name
     * @return String of procedure name.
     */
    String get();
}
