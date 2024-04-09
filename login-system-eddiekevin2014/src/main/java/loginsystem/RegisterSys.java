/**
 *
 */
package loginsystem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Registration System
 */
public class RegisterSys {

    // array lists to store user data and username
    private ArrayList<User> list = new ArrayList<User>();
    private ArrayList<String> usernameList = new ArrayList<String>();

    /**
     *
     */
    public RegisterSys() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Writing user data to file
     *
     * @param user
     * @throws FileNotFoundException
     */
    public void saveUser(User user) throws IOException {
        //create secured password
        Encrypter secret = new Encrypter();
        String securedPass = secret.sha256(user.getPassword());
        user.setPassword(securedPass);

        //format Date to String
        SimpleDateFormat sdf = new SimpleDateFormat(UserConfig.DATE_FORMAT);

        //build user data String
        String userStr = user.getUsername() + UserConfig.DELIMITER
                + user.getPassword() + UserConfig.DELIMITER
                + user.getFirstName() + UserConfig.DELIMITER
                + user.getLastName() + UserConfig.DELIMITER
                + sdf.format(user.getDob()) + UserConfig.DELIMITER
                + user.getGender() + UserConfig.DELIMITER
                + user.getEmail() + UserConfig.DELIMITER
                + user.getMobile();

        FileWriter fw = new FileWriter(UserConfig.USER_FILE, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter writer = new PrintWriter(bw);

        writer.println(userStr);
        writer.close();

        // add to user list
        list.add(user);
        usernameList.add(user.getUsername());
    }

    /**
     * Loads users from file
     *
     * @throws FileNotFoundException
     */
    public void loadUsers() throws FileNotFoundException, ParseException {
        String line;
        String[] headers;
        String[] userData;
        User user;

        //load data from file
        Scanner scan = new Scanner(new File(UserConfig.USER_FILE));

        //header
        line = scan.nextLine();
        headers = line.split(UserConfig.DELIMITER);

        if (!scan.hasNext()) {
            return;
        }
        //data
        line = scan.nextLine();
        while (line != null && !line.isEmpty()) {
            user = new User();

            // store data into string array
            userData = line.split(UserConfig.DELIMITER);
            for (int i = 0; i < headers.length; i++) {
                switch (headers[i]) {
                    case UserConfig.HEADER_USERNAME:
                        user.setUsername(userData[i].trim());
                        break;
                    case UserConfig.HEADER_PASSWORD:
                        user.setPassword(userData[i].trim());
                        break;
                    case UserConfig.HEADER_FIRST_NAME:
                        user.setFirstName(userData[i].trim());
                        break;
                    case UserConfig.HEADER_LAST_NAME:
                        user.setLastName(userData[i].trim());
                        break;
                    case UserConfig.HEADER_DOB:
                        SimpleDateFormat sdf = new SimpleDateFormat(UserConfig.DATE_FORMAT);
                        user.setDob(sdf.parse(userData[i].trim()));
                        break;
                    case UserConfig.HEADER_GENDER:
                        user.setGender(userData[i].trim());
                        break;
                    case UserConfig.HEADER_EMAIL:
                        user.setEmail(userData[i].trim());
                        break;
                    case UserConfig.HEADER_MOBILE:
                        user.setMobile(userData[i].trim());
                        break;

                }
            }
            // add to arrayList
            list.add(user);
            usernameList.add(user.getUsername());

            // read next line
            if (scan.hasNextLine()) {
                line = scan.nextLine();
            } else {
                line = null;
            }
        }
        scan.close(); // close reader
    }

    /**
     * Check to see if username is unique or not
     * @param accName
     * @return
     */
    public boolean isUniqueName(String accName) {
        boolean unique = true;
        for (int i = 0; i < usernameList.size(); i++) {
            String name = (String) usernameList.get(i);
            if (name.equals(accName)) {
                unique = false;
            }
        }
        return unique;
    }

    /**
     * @param username
     * @return
     */
    public User getUser(String username) {
        User user = null;
        for (int i = 0; i < list.size(); i++) {
            if (username.equals((String) usernameList.get(i))) {
                user = (User) list.get(i);
            }
        }
        return user;
    }

    /**
     * @return
     */
    public ArrayList<String> getUsernames() {
        return usernameList;
    }

    /**
     * Saving temporary password to file
     * @param username
     * @param tempPass
     */
    public void saveTempPass(String username, String tempPass) {
        //create secured password
        Encrypter secret = new Encrypter();
        String securedPass = secret.sha256(tempPass);

        try {
            FileWriter fw = new FileWriter(UserConfig.TEMP_PASS_FILE, true);
            BufferedWriter bw = new BufferedWriter(fw);
            try (PrintWriter writer = new PrintWriter(bw)) {
                writer.println(username + UserConfig.DELIMITER + securedPass);
            }
        } catch (IOException ex) {
            Logger.getLogger(RegisterSys.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //update real-time User object in List
        getUser(username).setPassword(securedPass);

    }
}
