package Application.Controller;

import Application.Controller.AbstractController;
import Application.Controller.ViewController;
import Application.SearchContainer;
import Domain.*;

/**
 * Interface to allow polymorphism between this applications Application.OLDCONTROLLERS.
 */
public interface Controllers {

    /**
     * Method that will reLoad the Application.NEWSTUFF.OLDCONTROLLERS.AbstractController with needed information.
     * This method can be used to pass information from one Application.NEWSTUFF.OLDCONTROLLERS.AbstractController to another.
     * <br><br>
     * <p>
     * <font color="Red">
     * IMPORTANT:
     * </font>
     * This method need to be overridden to work correctly.
     * </p>
     * Consult {@link ViewController} Enum class if custom values are needed.
     * @param string String passed to the new Application.NEWSTUFF.OLDCONTROLLERS.AbstractController
     * @see AbstractController
     */
    void initValues(String string);

    void initValues(Provider provider);

    void initValues(Education education);

    void initValues(Employee employee);

    void initValues(Interview interview);

    void initValues(Consultation consultation);

    void initValues(Company company);

    void initValues(SearchContainer searchContainer);

    void initValues(SearchContainer searchContainer, Provider provider);

    void initValues(SearchContainer searchContainer, Education education);

    void initValues(SearchContainer searchContainer, Employee employee);

    void initValues(SearchContainer searchContainer, Interview interview);

    void initValues(SearchContainer searchContainer, Consultation consultation);

    void initValues(SearchContainer searchContainer, Company company);

}
