package DevOpsTools;

import Domain.Education;
import Domain.Provider;
import Foundation.DB;
import Persistance.DbFacade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class TestDataCreator {

    //Finals
    private final String[] PROVIDER_PREFIX = {"Sønderborg", "Dybøl", "København", "Esbjerg", "Odense"};
    private final String[] PROVIDER_SUFFIX = {"VUC", "EUC", "Skole", "Academy", "EASV"};

    private final String[] EDUCATION_PREFIX = {"Bachelor", "Phd", "Cand", "Master", "A-Level"};
    private final String[] EDUCATION_AFFIX = {"Bio", "Design", "Mathematic", "Physic", "Media"};
    private final String[] EDUCATION_SUFFIX = {"Analystiker", "Reporter", "Teacher", "Designer", "Researcher"};

    private final String[] interviewNaming = {""};

    private final String[] employeePrefix = {""};
    private final String[] employeeSuffix = {""};

    private final String[] consultationNaming = {""};

    private final String[] companyPrefix = {""};
    private final String[] companyAffix = {""};
    private final String[] companySuffix = {""};

    private final String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sed aliquam est, quis tristique dolor. Mauris cursus quam sit amet nunc tempor, in cursus odio lobortis. Curabitur dictum, arcu et vehicula dignissim, tellus arcu hendrerit tortor, vel ultricies ipsum elit eu dui. In dignissim in neque non aliquam. Aliquam erat volutpat. Fusce at scelerisque odio. Pellentesque sit amet sapien velit. Nam fermentum turpis purus, non porta sem rhoncus ac. Nunc facilisis mauris risus, at suscipit velit vehicula at. Nunc laoreet vitae erat vel mollis. ";


    //Properties
    private final int NO_OF_PROVIDERS_GENERATED = 10;
    private final int NO_OF_EDUCATIONS_GENERATED = 20;
    private final int MAX_DATES_EACH_EDUCATION = 10;

    //Variables
    private ArrayList<Provider> providerArrayList = new ArrayList<>();
    private ArrayList<Education> educationArrayList = new ArrayList<>();

    public static void main(String[] args) { //TODO should hardcode a null test
        TestDataCreator t = new TestDataCreator();
        t.construct();
    }

    private void construct() {
        //Fill the providerArrayList
        for (int i = 0; i < NO_OF_PROVIDERS_GENERATED; i++) {
            providerArrayList.add(createProvider());
        }

        //Fill the educationArrayList
        for (int i = 0; i < NO_OF_EDUCATIONS_GENERATED; i++) {
            educationArrayList.add(createEducation());
        }

        DB database = DB.getInstance();
        for (Education education : educationArrayList) {
            try {
                database.connect();
                DbFacade.insertEducationToBatch(education);
                database.executeBatch();
                database.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private Provider createProvider() {
        //Build provider name
        StringBuilder providerName = new StringBuilder();
        providerName.append(PROVIDER_PREFIX[randomInt(0, PROVIDER_PREFIX.length-1)]);
        providerName.append(" ");
        providerName.append(PROVIDER_SUFFIX[randomInt(0, PROVIDER_SUFFIX.length-1)]);

        return new Provider(null, providerName.toString());
    }

    private Education createEducation() {
        //Build education name
        StringBuilder educationName = new StringBuilder();
        educationName.append(EDUCATION_PREFIX[randomInt(0, EDUCATION_PREFIX.length-1)]);
        educationName.append(" ");
        educationName.append(EDUCATION_AFFIX[randomInt(0, EDUCATION_AFFIX.length-1)]);
        educationName.append(" ");
        educationName.append(EDUCATION_SUFFIX[randomInt(0, EDUCATION_SUFFIX.length-1)]);

        //Build date arrayList
        ArrayList<Date> dates = new ArrayList<>();
        for (int i = 0; i < MAX_DATES_EACH_EDUCATION; i++) {
            dates.add(randomDate());
        }

        return new Education(null, educationName.toString(), loremIpsum, randomInt(0, 5), dates,providerArrayList.get(randomInt(0,providerArrayList.size()-1)));
    }

    private Date randomDate(){
        Random rnd = new Random();
        long ms = -946771200000L + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));
        return new Date(ms);
    }

    int randomInt(int min, int max) {
        int range = Math.abs(max - min) + 1;
        return (int) (Math.random() * range) + (min <= max ? min : max);
    }
}
