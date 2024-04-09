/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Check if password valid
 * Generate strong password
 * 
 */
public class PassChecker {
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String PUNCTUATION = "!@#&()–[{}]:;',?/*~$^+=<>";
    
    ArrayList<String> list = new ArrayList<>();
    Pattern pattern = Pattern.compile(UserConfig.PASSWORD_PATTERN);
    
    /**
     * 
     */
    public PassChecker(){
    }
    
    /**
     * Load bad password from file
     */
    private void loadBadPass(){       
        String line;       
        //load  from file
        Scanner scan = null;
        try {
            scan = new Scanner(new File(UserConfig.BAD_PASS_FILE));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PassChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
        //add to list
        while (scan != null && scan.hasNext()){
            line = scan.nextLine().trim();
            list.add(line);
        }
        if (scan != null){
            scan.close();
        }
        
    }
    
    /**
     * Return if password is "bad"
     * @param password
     * @return 
     */
    public boolean isBadPass(String password){
        if (list.isEmpty()){
            loadBadPass();
        }
        boolean bad = false;
        for (int i = 0; i < list.size(); i++){
            String passStr = (String)list.get(i);
            if (passStr.equals(password)) {
                bad = true;
                break;
            }
       }  
        return bad;
    }
    
     /**
     * Return if password follows rules
     * @param password
     * @return boolean
     */
    public boolean isPassValid(String password){
        boolean valid = false;
        Matcher matcher = pattern.matcher(password);
        valid = matcher.matches();
        return valid;
    }
    
    /**
     * Generating the temporary password
     * @param length
     * @return String
     */
    public String generatePassword(int length) {
        //initialize
        String combinedChars = LOWER + UPPER + PUNCTUATION + DIGITS;
        Random random = new Random();
        StringBuilder passbuilder = new StringBuilder();
        char[] password = new char[length];
        
        // generate each char of password
        password[0] = LOWER.charAt(random.nextInt(LOWER.length()));
        password[1] = UPPER.charAt(random.nextInt(UPPER.length()));
        password[2] = PUNCTUATION.charAt(random.nextInt(PUNCTUATION.length()));
        password[3] = DIGITS.charAt(random.nextInt(DIGITS.length()));
        for (int i = 4; i < length; i++) {
            password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
        }
        
        //re-sequence
        List<Character> charList = new ArrayList<Character>();
        for(char ch : password){
            charList.add(ch);
        }
        Collections.shuffle(charList);
        for (int i = 0; i < charList.size(); i++) {
            passbuilder.append((char) charList.get(i));
        }
        
        //return
        return passbuilder.toString();
    }
    
}
