/**
 *
 */
package loginsystem;

/**
 * List of public variables and data
 */
public class UserConfig {

    public static final String USER_FILE = "UserData.csv";
    public static final String TEMP_PASS_FILE = "TempPass.csv";
    public static final String BAD_PASS_FILE = "dictbadpass.txt";

    public static final String DELIMITER = ",";

    public static final String DATE_FORMAT = "dd-MMM-yyyy";

    public static final String HEADER_USERNAME = "User ID";
    public static final String HEADER_PASSWORD = "Password";
    public static final String HEADER_FIRST_NAME = "First Name";
    public static final String HEADER_LAST_NAME = "Last Name";
    public static final String HEADER_DOB = "Date of Birth";
    public static final String HEADER_GENDER = "Gender";
    public static final String HEADER_OCCUPATION = "Occupation";
    public static final String HEADER_ADDRESS = "Address";
    public static final String HEADER_EMAIL = "Email";
    public static final String HEADER_MOBILE = "Mobile";

    public static final String TITLE = "Registration System";
    public static final String FORM_LOGIN = "Login Form";
    public static final String FORM_FORGET_PASS = "Forget Password";
    public static final String FORM_SIGN = "Sign-up Form";
    public static final String FORGET_PASS = "Forget Password";
    public static final String BACK_LOGIN = "Back to Login";

    public static final String DATES[] = {
        "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15",
        "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
    public static final String MONTHS[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    public static final String YEARS[] = {
        "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980",
        "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990",
        "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000",
        "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010",
        "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020",
        "2021", "2022", "2023", "2024"};

    public static final String GENDER_M = "Male";
    public static final String GENDER_F = "Female";

    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static final String PASSWORD_RULES = "Minimum one digit [0-9] \n"
            + "Minimum one lowercase character [a-z] \n"
            + "Minimum one uppercase character [A-Z] \n"
            + "Minimum one special character in this selection [!@#&()–[{}]:;',?/*~$^+=<>] \n"
            + "Length of at least 8 characters and maximum of 50 characters";

    public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,50}$";

    public static final String PHONE_PATTERN = "(###)###-####";

}
