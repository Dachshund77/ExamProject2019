package Domain.DomainObjects;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Education implements Domain{
    private final Integer amuNr;
    private String educationName;
    private String description;
    private Integer noOfDays;
    private ArrayList<LocalDate> dates;
    private Provider provider;

    private static final int EDUCATION_NAME_MAX_LENGTH = 50;
    private static final int NO_OF_DAYS_MAX_NUMBER = 15;

    public Education(Integer amuNr, String educationName, String description, Integer noOfDays, ArrayList<LocalDate> dates, Provider provider) {
        this.amuNr = amuNr;
        setEducationName(educationName);
        setDescription(description);
        this.noOfDays = noOfDays;
        this.dates = Objects.requireNonNullElseGet(dates, ArrayList::new);
        this.provider = provider;
    }

    public Integer getAmuNr() {
        return amuNr;
    }

    public String getEducationName() {
        return educationName;
    }

    public String getDescription() {
        return description;
    }

    public Integer getNoOfDays() {
        return noOfDays;
    }

    public ArrayList<LocalDate> getDates() {
        return dates;
    }

    public Provider getProvider() {
        return provider;
    }

    public static int getEducationNameMaxLength() {
        return EDUCATION_NAME_MAX_LENGTH;
    }

    public static int getNoOfDaysMaxNumber() {
        return NO_OF_DAYS_MAX_NUMBER;
    }

    /**
     * Converts empty String to null.
     * @param educationName new educationName
     */
    public void setEducationName(String educationName) {
        if (educationName == null || educationName.trim().isEmpty()){
            this.educationName = null;
        } else {
            this.educationName = educationName;
        }
    }

    /**
     * Converts empty String to null
     * @param description new description.
     */
    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()){
            this.description = null;
        }else {
            this.description = description;
        }
    }

    public void setDates(ArrayList<LocalDate> dates) {
        this.dates = dates;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    /**
     * Amu nr may not be negative.
     * @param amuNr Integer to be tested.
     * @return True if valid.
     */
    public static boolean isValidAmuNr(Integer amuNr){
        return amuNrInvalidCause(amuNr)== null;
    }

    /**
     * Amu nr may not be negative.
     * @param amuNr String to be tested.
     * @return True if valid.
     */
    public static boolean isValidAmuNr(String amuNr){
        return amuNrInvalidCause(amuNr) == null;
    }

    /**
     * Return the first reason why an amuNr is invalid.
     * @param amuNr Integer to be tested.
     * @return String with first problem, null if valid.
     */
    public static String amuNrInvalidCause(Integer amuNr){
        if (amuNr != null && amuNr <= 0){
            return "Amu Nr must be positive!";
        }
        return null;
    }

    /**
     * Return the first reason why an amuNr is invalid.
     * @param amuNr String to be tested.
     * @return String with first problem, null if valid.
     */
    public static String amuNrInvalidCause(String amuNr){
        if (amuNr == null){
            return null;
        }
        if (amuNr.trim().isEmpty()){
            return null;
        }
        try{
            return amuNrInvalidCause(Integer.parseInt(amuNr));
        }catch (NumberFormatException e){
            return "Must be a number!";
        }
    }

    /**
     * checks if educationName is valid
     * @param educationName String to be tested.
     * @return True if valid.
     */
    public static boolean isValidEducationName(String educationName)
    {
      return educationNameInvalidCause(educationName) == null;
    }

    /**
     * throws an error if
     * educationName = NULL
     * educationName = Empty string
     * educationName = Bigger than 30 chars
     * @param educationName String to be tested.
     * @return String with first problem, null if valid.
     */
    public static String educationNameInvalidCause (String educationName)
    {
        if (educationName == null){
            return "Education cant be null";
        }
        else if (educationName.trim().isEmpty()){
            return "Education must have a name";
        }
        else if (educationName.length() > EDUCATION_NAME_MAX_LENGTH){
            return "Education must consist of a name less than "+EDUCATION_NAME_MAX_LENGTH+" characters";
        }
        return null;
    }

    /**
     * checks if description contains information about a course
     * @param description String to be tested.
     * @return True if valid.
     */
    public static boolean isValidDescription(String description)
    {
        return descriptionInvalidCause(description) == null;
    }

    /**
     * yeah this test is kinda pointless since it never not null
     * @param description string to be tested.
     * @return String with first problem, null if valid.
     */
    public static String descriptionInvalidCause (String description)
    {
        if (description == null){
            return null;
        }
        else if (description.trim().isEmpty()) {
            return null; //TODO this test is never not null..........
        }
        return null;
    }

    /**
     * checks if NoOfDays arent null and within limit
     * @param noOfDays Integer to be tested
     * @return True if valid.
     */
    public static boolean isValidNoOfDays(Integer noOfDays)
    {
        return noOfDaysInvalidCause(noOfDays) == null;
    }

    /**
     * checks if NoOfDays arent null and within limit
     * @param noOfDays String to be tested.
     * @return True if valid.
     */
    public static boolean isValidNoOfDays(String noOfDays)
    {
        return noOfDaysInvalidCause(noOfDays) == null;
    }

    /**
     * Throws an error if:
     * NoOfDays = NULL
     * NoOfDays = Negative number
     * NoOfDays = Bigger than 15
     * @param NoOfDays Integer to be tested.
     * @return String with first found problem, null if valid.
     */
    public static String noOfDaysInvalidCause(Integer NoOfDays)
    {
       if (NoOfDays == null){
           return "Number of days cant be null";
       }
       else if (NoOfDays > NO_OF_DAYS_MAX_NUMBER){
           return "Number of days cant be more than "+NO_OF_DAYS_MAX_NUMBER+"!";
       }
       else if (NoOfDays <= 0){
           return "Number of days must be positive!";
       }
        return null;
    }

    /**
     * Throws an error if:
     * NoOfDays = NULL
     * NoOfDays = Negative number
     * NoOfDays = Bigger than 15
     * @param noOfDays String to be tested.
     * @return String with first found problem,null if valid.
     */
    public static String noOfDaysInvalidCause(String noOfDays) {
        if (noOfDays == null){
            return "Number of days may not be null!";
        }if (noOfDays.trim().isEmpty()){
            return "Number of days may not be empty!";
        }
        try{
            return noOfDaysInvalidCause(Integer.parseInt(noOfDays));
        }catch (NumberFormatException e){
            return "Must be a number!";
        }
    }

    @Override
    public String toString() {
        return educationName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (!(obj instanceof Education)) {
            return false;
        }
        Education other = (Education) obj;
        if (this == other){
            return true;
        }
        //Test all fields
        if (!Objects.equals(this.amuNr,other.amuNr)){
            return false;
        }
        if (!Objects.equals(this.educationName,other.educationName)){
            return false;
        }
        if (!Objects.equals(this.description,other.description)){
            return false;
        }
        if (!Objects.equals(this.noOfDays,other.noOfDays)){
            return false;
        }
        if (!Objects.equals(this.dates,other.dates)){
            return false;
        }
        return Objects.equals(this.provider,other.provider);

    }
}
