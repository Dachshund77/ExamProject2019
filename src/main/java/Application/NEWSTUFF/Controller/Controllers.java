package Application.NEWSTUFF.Controller;

import Application.SearchContainer;
import Domain.*;

/**
 * Interface to allow polymorphism between this applications Application.Controller.
 */
public interface Controllers {

    /**
     * Method that will reLoad the Application.NEWSTUFF.Controller.AbstractController with needed information.
     * This method can be used to pass information from one Application.NEWSTUFF.Controller.AbstractController to another.
     * <br><br>
     * <p>
     * <font color="Red">
     * IMPORTANT:
     * </font>
     * This method need to be overridden to work correctly.
     * </p>
     * Consult {@link ViewController} Enum class if custom values are needed.
     * @param string String passed to the new Application.NEWSTUFF.Controller.AbstractController
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
