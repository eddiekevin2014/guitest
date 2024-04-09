/**
 *
 */
package loginsystem;
import java.security.*;
import java.util.logging.*;

/**
 * Encrypt
 */
public class Encrypter {

    /**
     * Constructor
     */
    public Encrypter() {
        // TODO Auto-generated constructor stub
    }
    
    /**
     * Password encryption method
     * @param password
     * @return 
     */
    public String sha256(String password){
        String encryptedString = "";
        //java helper class to perform encryption
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
            //give the helper function the password
            md.update(password.getBytes());
            //perform the encryption
            byte byteData[] = md.digest();
            //To express the byte data as a hexadecimal number (the normal way)
            for (int i = 0; i < byteData.length; i++) {
                encryptedString += (Integer.toHexString((0xFF & byteData[i])|0x100).substring(1, 3));
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Encrypter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return encryptedString;
    }


}
