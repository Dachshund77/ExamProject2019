package DevOpsTools;

import Domain.*;
import Foundation.DB;
import Persistance.DbFacade;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("Duplicates")
public class TestDataCreator {

    //Finals
    private final String[] PROVIDER_PREFIX = {"Sønderborg", "Dybøl", "København", "Esbjerg", "Odense"};
    private final String[] PROVIDER_SUFFIX = {"VUC", "EUC", "Skole", "Academy", "EASV"};

    private final String[] EDUCATION_PREFIX = {"Bachelor", "Phd", "Cand", "Master", "A-Level"};
    private final String[] EDUCATION_AFFIX = {"Bio", "Design", "Mathematic", "Physic", "Media"};
    private final String[] EDUCATION_SUFFIX = {"Analystiker", "Reporter", "Teacher", "Designer", "Researcher"};

    private final String[] INTERVIEW_PREFIX = {"Stress","Blue","Red","First","Second","Thirds"};
    private final String[] INTERVIEW_SUFFIX = {"Meeting","Consultation","Get together"};

    private final String[] EMPLOYEE_PREFIX = {"Sven","Mathias","Anders","Eric","Max","Peter","Frank","Maria","Anna",};
    private final String[] EMPLOYEE_SUFFIX = {"Petersen","Büchner","Sørensen","Hendriksen","Christensen","Jessen"};

    private final String[] CONSULTATION_AFFIX = {"Consultation A", "Consultation B", "Cconsultation C"};

    private final String[] COMPANY_PREFIX = {"RED","BLUE"};
    private final String[] COMPANY_AFFIX = {"HERING","Factory"};
    private final String[] COMPANY_SUFFIX = {"A/S","GMBH","Private investor"};

    private final String loremIpsum = "Nunc facilisis mauris risus, at suscipit velit vehicula at. Nunc laoreet vitae erat vel mollis. ";


    //Properties
    private final int NO_OF_PROVIDERS_GENERATED = 3;
    private final int NO_OF_EDUCATIONS_GENERATED = 5;
    private final int NO_OF_COMPANIES_GENERATED = 20;
    private final int MAX_DATES_EACH_EDUCATION = 3;
    private final int MAX_EDUCATION_WISHES_PER_INTERVIEW = 3;
    private final int MAX_FINISHED_EDUCATION_PER_INTERVIEW = 3;
    private final int MAX_INTERVIEWS_PER_EMPLOYEE = 3;
    private final int MAX_EMPLOYEE_PER_CONSULTATION = 3;
    private final int MAX_CONSULTATION_PER_COMPANY = 3;
    private final int MAX_EDUCATIONS_PER_COMANY= 5;

    //Variables
    private ArrayList<Provider> providerArrayList = new ArrayList<>();
    private ArrayList<Education> educationArrayList = new ArrayList<>();
    private ArrayList<Company> companyArayList = new ArrayList<>();

    public static void main(String[] args) { //TODO should hardcode a null test
        TestDataCreator t = new TestDataCreator();
        t.constructEducation();
        t.constructCompany();
    }

    private void constructEducation() {
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
                DbFacade.insertEducation(education);
                database.executeBatch();
                database.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void constructCompany(){
        //Fill the educationArrayList
        for (int i = 0; i < NO_OF_COMPANIES_GENERATED; i++) {
            companyArayList.add(createCompany());
        }

        DB database = DB.getInstance();
        for (Company company : companyArayList) {
            try {
                database.connect();

                DbFacade.insertCompany(company);
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
        ArrayList<LocalDate> dates = new ArrayList<>();
        for (int i = 0; i < MAX_DATES_EACH_EDUCATION; i++) {
            dates.add(randomDate());
        }

        return new Education(null, educationName.toString(), loremIpsum, randomInt(0, 5), dates,providerArrayList.get(randomInt(0,providerArrayList.size()-1)));
    }

    private EducationWish createEducationWish(){

        Integer priority = randomInt(1,5);
        Education randomEducation = educationArrayList.get(randomInt(0,educationArrayList.size()-1));

        return new EducationWish(null,randomEducation,priority);
    }

    private FinishedEducation createFinishedEducation(){

        Education randomEducation = educationArrayList.get(randomInt(0,educationArrayList.size()-1));
        LocalDate randomDate = randomDate();

        return new FinishedEducation(null,randomEducation,randomDate);
    }

    private Interview createInterview(){

        StringBuilder interviewName = new StringBuilder();
        interviewName.append(INTERVIEW_PREFIX[randomInt(0,INTERVIEW_PREFIX.length-1)]);
        interviewName.append(" ");
        interviewName.append(INTERVIEW_SUFFIX[randomInt(0, INTERVIEW_SUFFIX.length-1)]);

        Integer productUnderstanding = randomInt(0,5);
        Integer problemUnderstanding = randomInt(0,5);
        Integer flexibility = randomInt(0,5);
        Integer qualityAwareness = randomInt(0,5);
        Integer cooperat = randomInt(0,5);

        ArrayList<EducationWish> educationWishes = new ArrayList<>();

        for (int i = 0; i < MAX_EDUCATION_WISHES_PER_INTERVIEW; i++) {
            educationWishes.add(createEducationWish());
        }

        ArrayList<FinishedEducation> finishedEducation = new ArrayList<>();
        for (int i = 0; i < MAX_FINISHED_EDUCATION_PER_INTERVIEW; i++) {
            finishedEducation.add(createFinishedEducation());
        }

        return new Interview(null,interviewName.toString(),productUnderstanding,problemUnderstanding,flexibility,qualityAwareness,cooperat,finishedEducation,educationWishes);

    }

    private Employee createEmployee(){
        String firstName = EMPLOYEE_PREFIX[randomInt(0,EMPLOYEE_PREFIX.length-1)];
        String lastName = EMPLOYEE_SUFFIX[randomInt(0,EMPLOYEE_SUFFIX.length-1)];
        String cprNr = Integer.toString(randomInt(0,111111));
        String phoneNr = Integer.toString(randomInt(0,111111));
        ArrayList<Interview> interviews = new ArrayList<>();
        for (int i = 0; i < MAX_INTERVIEWS_PER_EMPLOYEE; i++) {
            interviews.add(createInterview());
        }

        return new Employee(null,firstName, lastName,cprNr,"aaa@mail",phoneNr,interviews);
    }

    private Consultation createConsultation(){
        String consultationName = CONSULTATION_AFFIX[randomInt(0,CONSULTATION_AFFIX.length-1)];

        ArrayList<Employee> employees = new ArrayList<>();
        for (int i = 0; i < MAX_EMPLOYEE_PER_CONSULTATION; i++) {
            employees.add(createEmployee());

        }

        return new Consultation(null,consultationName,randomDate(),randomDate(),employees);
    }

    private Company createCompany(){
        String cvrNr = Integer.toString(randomInt(0,111111));

        StringBuilder companyName = new StringBuilder();
        companyName.append(COMPANY_SUFFIX[randomInt(0, COMPANY_SUFFIX.length-1)]);
        companyName.append(" ");
        companyName.append(COMPANY_AFFIX[randomInt(0,COMPANY_AFFIX.length-1)]);
        companyName.append(" ");
        companyName.append(COMPANY_PREFIX[randomInt(0,COMPANY_PREFIX.length-1)]);

        ArrayList<Consultation> consultations = new ArrayList<>();
        for (int i = 0; i < MAX_CONSULTATION_PER_COMPANY; i++) {
            consultations.add(createConsultation());
        }

        ArrayList<Education> educations  = new ArrayList<>();
        for (int i = 0; i < MAX_EDUCATIONS_PER_COMANY; i++) {
            educations.add(createEducation());
        }

        return new Company(null,cvrNr, companyName.toString(),consultations,educations);
    }

    private LocalDate randomDate(){ //TODO parameterize and maybe understand that damn code block
        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2015, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        return randomDate;
    }

    int randomInt(int min, int max) {
        long range = Math.abs(max - min) + 1;
        return (int) (Math.random() * range) + (min <= max ? min : max);
    }

}
