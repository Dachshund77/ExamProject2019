package Domain.DomainObjects;

import java.util.ArrayList;
import java.util.Objects;

public class Company implements Domain{


    private final Integer companyID;
    private String cvrNr;
    private String companyName;
    private ArrayList<Consultation> consultations;

    private static final int CVR_EXACT_LENGTH = 8;
    private static final int COMPANY_NAME_MAX_LENGTH = 50;

    public Company(Integer companyID, String cvrNr, String companyName, ArrayList<Consultation> consultations) {
        this.companyID = companyID;
        setCvrNr(cvrNr);
        setCompanyName(companyName);
        this.consultations = Objects.requireNonNullElseGet(consultations, ArrayList::new);
    }


    public Integer getCompanyID() {
        return companyID;
    }

    public String getCvrNr() {
        return cvrNr;
    }

    public String getCompanyName() {
        return companyName;
    }

    public ArrayList<Consultation> getConsultations() {
        return consultations;
    }

    public static int getCvrExactLength() {
        return CVR_EXACT_LENGTH;
    }

    public static int getCompanyNameMaxLength() {
        return COMPANY_NAME_MAX_LENGTH;
    }

    /**
     * Converts empty strings to null.
     *
     * @param cvrNr New cvrNr
     */
    public void setCvrNr(String cvrNr) {
        if (cvrNr == null || cvrNr.trim().isEmpty()) {
            this.cvrNr = null;
        } else {
            this.cvrNr = cvrNr;
        }
    }

    /**
     * Convert empty String to null.
     *
     * @param companyName new companyName
     */
    public void setCompanyName(String companyName) {
        if (companyName == null || companyName.trim().isEmpty()) {
            this.companyName = null;
        } else {
            this.companyName = companyName;
        }
    }

    /**
     * A valid ID may not be negative.
     *
     * @param companyID ID to be tested
     * @return True if valid
     */
    public static boolean isValidCompanyID(Integer companyID) {
        return companyIDInvalidCause(companyID) == null;
    }

    /**
     * A valid ID may not be negative.
     *
     * @param companyID ID to be tested
     * @return True if valid
     */
    public static boolean isValidCompanyID(String companyID) {
        return companyIDInvalidCause(companyID) == null;
    }

    /**
     * Return the first reason why the Integer is not valid ID.
     * A valid ID may not be negative.
     *
     * @param companyID ID that is invalid
     * @return String with reason, null if none are detected.
     */
    public static String companyIDInvalidCause(Integer companyID) {
        if (companyID != null && companyID <= 0) {
            return "Company ID must be positive!";
        }
        return null;
    }

    /**
     * Return the first reason why the String is not valid ID.
     * A valid ID may not be negative.
     *
     * @param companyID ID that is invalid
     * @return String with reason, null if none are detected.
     */
    public static String companyIDInvalidCause(String companyID) {
        if (companyID == null) {
            return null;
        }
        if (companyID.trim().isEmpty()) {
            return null;
        }
        try {
            return companyIDInvalidCause(Integer.parseInt(companyID));
        } catch (NumberFormatException e) {
            return "Must be a number!";
        }
    }

    /**
     * A valid cvrNr may not be null, empty String and must be Char long.
     *
     * @param cvrNr cvr To be tested
     * @return True if cvr is valid
     */
    public static boolean isValidCvrNr(String cvrNr) {
        return cvrNrInvalidCause(cvrNr) == null;
    }

    /**
     * Return the first reason why a cvr is not valid.
     * A valid cvrNr may not be null, empty String and must be 8 Char long.
     *
     * @param cvrNr cvr to be tested
     * @return String with first reason, null if non was detected.
     */
    public static String cvrNrInvalidCause(String cvrNr) {
        if (cvrNr == null) {
            return "CvrNr may not be Null!";
        } else if (cvrNr.trim().isEmpty()) {
            return "CvrNr may not be empty!";
        } else if (!cvrNr.matches("[0-9]+")) {
            return "CvrNr may not contain letters!";
        } else if (cvrNr.length() != CVR_EXACT_LENGTH) {
            return "CvrNr must be " + CVR_EXACT_LENGTH + " characters long!";
        }
        return null;
    }

    /**
     * A valid Company name may not be null, empty String or Longer than 50 Chars.
     *
     * @param name company name to be tested.
     * @return True if name is valid.
     */
    public static boolean isValidCompanyName(String name) {
        return companyNameInvalidClause(name) == null;
    }

    /**
     * Return the first reason why a String is a invalid company Name.
     * A valid Company name may not be null, empty String or Longer than 50 Chars.
     *
     * @param name String that should be the Company name
     * @return String with first reason, null if non was detected.
     */
    public static String companyNameInvalidClause(String name) {
        if (name == null) {
            return "Company Name may not be Null!";
        } else if (name.trim().isEmpty()) {
            return "Company Name may not be empty!";
        } else if (name.length() > COMPANY_NAME_MAX_LENGTH) {
            return "Company Name must be " + COMPANY_NAME_MAX_LENGTH + " letters or less!";
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (!(obj instanceof Company)) {
            return false;
        }
        Company other = (Company) obj;
        if (this == other) {
            return true;
        }
        //Test all fields
        if (!Objects.equals(this.companyID, other.companyID)) {
            return false;
        }

        if (!Objects.equals(this.cvrNr, other.cvrNr)) {
            return false;
        }

        if (!Objects.equals(this.companyName, other.companyName)) {
            return false;
        }

        return Objects.equals(this.consultations, other.consultations);
    }
}
