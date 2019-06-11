package Application.Controller;

import Application.SearchContainer;
import Domain.DomainObjects.*;

/**
 * Interface to allow polymorphism between this applications Controllers.
 */
public interface Controllers {

    /**
     * Method that will load the controller with needed information.
     * This method can be used to pass information from one Controller to another.
     * <br><br>
     * <p>
     * <font color="Red">
     * IMPORTANT:
     * </font>
     * This method need to be overridden to work correctly.
     * </p>
     * Consult {@link ViewController} Enum class if custom values are needed.
     * @param string Object passed to the new Controller
     * @see AbstractController
     */
    void initValues(String string);

    /**
     * Method that will load the controller with needed information.
     * This method can be used to pass information from one Controller to another.
     * <br><br>
     * <p>
     * <font color="Red">
     * IMPORTANT:
     * </font>
     * This method need to be overridden to work correctly.
     * </p>
     * Consult {@link ViewController} Enum class if custom values are needed.
     * @param provider Object passed to the new Controller
     * @see AbstractController
     */
    void initValues(Provider provider);

    /**
     * Method that will load the controller with needed information.
     * This method can be used to pass information from one Controller to another.
     * <br><br>
     * <p>
     * <font color="Red">
     * IMPORTANT:
     * </font>
     * This method need to be overridden to work correctly.
     * </p>
     * Consult {@link ViewController} Enum class if custom values are needed.
     * @param education Object passed to the new Controller
     * @see AbstractController
     */
    void initValues(Education education);

    /**
     * Method that will load the controller with needed information.
     * This method can be used to pass information from one Controller to another.
     * <br><br>
     * <p>
     * <font color="Red">
     * IMPORTANT:
     * </font>
     * This method need to be overridden to work correctly.
     * </p>
     * Consult {@link ViewController} Enum class if custom values are needed.
     * @param employee Object passed to the new Controller
     * @see AbstractController
     */
    void initValues(Employee employee);

    /**
     * Method that will load the controller with needed information.
     * This method can be used to pass information from one Controller to another.
     * <br><br>
     * <p>
     * <font color="Red">
     * IMPORTANT:
     * </font>
     * This method need to be overridden to work correctly.
     * </p>
     * Consult {@link ViewController} Enum class if custom values are needed.
     * @param employee Object passed to the new Controller
     * @see AbstractController
     */
    void initValues(Interview interview, Employee employee);

    /**
     * Method that will load the controller with needed information.
     * This method can be used to pass information from one Controller to another.
     * <br><br>
     * <p>
     * <font color="Red">
     * IMPORTANT:
     * </font>
     * This method need to be overridden to work correctly.
     * </p>
     * Consult {@link ViewController} Enum class if custom values are needed.
     * @param interview Object passed to the new Controller
     * @see AbstractController
     */
    void initValues(Interview interview);

    /**
     * Method that will load the controller with needed information.
     * This method can be used to pass information from one Controller to another.
     * <br><br>
     * <p>
     * <font color="Red">
     * IMPORTANT:
     * </font>
     * This method need to be overridden to work correctly.
     * </p>
     * Consult {@link ViewController} Enum class if custom values are needed.
     * @param consultation Object passed to the new Controller
     * @see AbstractController
     */
    void initValues(Consultation consultation);

    /**
     * Method that will load the controller with needed information.
     * This method can be used to pass information from one Controller to another.
     * <br><br>
     * <p>
     * <font color="Red">
     * IMPORTANT:
     * </font>
     * This method need to be overridden to work correctly.
     * </p>
     * Consult {@link ViewController} Enum class if custom values are needed.
     * @param company Object passed to the new Controller
     * @see AbstractController
     */
    void initValues(Company company);

    /**
     * Method that will load the controller with needed information.
     * This method can be used to pass information from one Controller to another.
     * <br><br>
     * <p>
     * <font color="Red">
     * IMPORTANT:
     * </font>
     * This method need to be overridden to work correctly.
     * </p>
     * Consult {@link ViewController} Enum class if custom values are needed.
     * @param searchContainer Object passed to the new Controller
     * @see AbstractController
     */
    void initValues(SearchContainer searchContainer);

    /**
     * Method that will load the controller with needed information.
     * This method can be used to pass information from one Controller to another.
     * <br><br>
     * <p>
     * <font color="Red">
     * IMPORTANT:
     * </font>
     * This method need to be overridden to work correctly.
     * </p>
     * Consult {@link ViewController} Enum class if custom values are needed.
     * @param searchContainer Object passed to the new Controller
     * @param provider Object passed to the new Controller
     * @see AbstractController
     */
    void initValues(SearchContainer searchContainer, Provider provider);

    /**
     * Method that will load the controller with needed information.
     * This method can be used to pass information from one Controller to another.
     * <br><br>
     * <p>
     * <font color="Red">
     * IMPORTANT:
     * </font>
     * This method need to be overridden to work correctly.
     * </p>
     * Consult {@link ViewController} Enum class if custom values are needed.
     * @param searchContainer Object passed to the new Controller
     * @param education Object passed to the new Controller
     * @see AbstractController
     */
    void initValues(SearchContainer searchContainer, Education education);

    /**
     * Method that will load the controller with needed information.
     * This method can be used to pass information from one Controller to another.
     * <br><br>
     * <p>
     * <font color="Red">
     * IMPORTANT:
     * </font>
     * This method need to be overridden to work correctly.
     * </p>
     * Consult {@link ViewController} Enum class if custom values are needed.
     * @param searchContainer Object passed to the new Controller
     * @param employee Object passed to the new Controller
     * @see AbstractController
     */
    void initValues(SearchContainer searchContainer, Employee employee);

    /**
     * Method that will load the controller with needed information.
     * This method can be used to pass information from one Controller to another.
     * <br><br>
     * <p>
     * <font color="Red">
     * IMPORTANT:
     * </font>
     * This method need to be overridden to work correctly.
     * </p>
     * Consult {@link ViewController} Enum class if custom values are needed.
     * @param searchContainer Object passed to the new Controller
     * @param interview Object passed to the new Controller
     * @see AbstractController
     */
    void initValues(SearchContainer searchContainer, Interview interview);

    /**
     * Method that will load the controller with needed information.
     * This method can be used to pass information from one Controller to another.
     * <br><br>
     * <p>
     * <font color="Red">
     * IMPORTANT:
     * </font>
     * This method need to be overridden to work correctly.
     * </p>
     * Consult {@link ViewController} Enum class if custom values are needed.
     * @param searchContainer Object passed to the new Controller
     * @param interview Object passed to the new Controller
     * @see AbstractController
     */
    void initValues(SearchContainer searchContainer, Interview interview, Employee employee);

    /**
     * Method that will load the controller with needed information.
     * This method can be used to pass information from one Controller to another.
     * <br><br>
     * <p>
     * <font color="Red">
     * IMPORTANT:
     * </font>
     * This method need to be overridden to work correctly.
     * </p>
     * Consult {@link ViewController} Enum class if custom values are needed.
     * @param searchContainer Object passed to the new Controller
     * @param consultation Object passed to the new Controller
     * @see AbstractController
     */
    void initValues(SearchContainer searchContainer, Consultation consultation);

    /**
     * Method that will load the controller with needed information.
     * This method can be used to pass information from one Controller to another.
     * <br><br>
     * <p>
     * <font color="Red">
     * IMPORTANT:
     * </font>
     * This method need to be overridden to work correctly.
     * </p>
     * Consult {@link ViewController} Enum class if custom values are needed.
     * @param searchContainer Object passed to the new Controller
     * @param company Object passed to the new Controller
     * @see AbstractController
     */
    void initValues(SearchContainer searchContainer, Company company);

}
