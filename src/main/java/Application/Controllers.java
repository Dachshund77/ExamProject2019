package Application;

/**
 * Interface to allow polymorphism between this applications Application.Controller.
 */
public interface Controllers {

    /**
     * Method that will reLoad the Application.AbstractController with needed information.
     * This method can be used to pass information from one Application.AbstractController to another.
     * <br><br>
     * <p>
     * <font color="Red">
     * IMPORTANT:
     * </font>
     * This method need to be overridden to work correctly.
     * </p>
     * Consult {@link ViewController} Enum class if custom values are needed.
     * @param string String passed to the new Application.AbstractController
     * @see AbstractController
     */
    void initValues(String string);
}
