import java.util.ArrayList;
import java.util.Locale;
import java.util.*;


/**
 * @Author John Mobarry
 */
public class PasswordCheckerUtility  {

    private static ArrayList<String> IllegalPasswords;



    /**
     * This method will check the validity of one password and return true if the password is valid and throw one or more exception if invalid
     * @param password
     * @return true/false based on validity of password
     * @throws NoSpecialCharacterException
     * @throws LengthException
     * @throws NoDigitException
     * @throws NoUpperAlphaException
     * @throws NoLowerAlphaException
     * @throws InvalidSequenceException
     */
    public static boolean isValidPassword(String password) throws NoSpecialCharacterException, LengthException, NoDigitException, NoUpperAlphaException, NoLowerAlphaException, InvalidSequenceException {


        char first = password.charAt(0);

        //
        if (password.length() < 6) {
            throw new LengthException("The password must be at least 6 characters long");
        }

        //
       // if (!Character.isDigit(first)) {

            boolean hasDigit = false;

            for (int i = 0; i < password.length(); i++) {

                first = password.charAt(i);
                if (Character.isDigit(first)) {
                    hasDigit = true;
                }
            }
            if (!hasDigit) {
                throw new NoDigitException("The password must contain at least one digit.");
            }
        //}

        //
       // if (!Character.isUpperCase(first)) {
            boolean hasUppercase = !password.equals(password.toLowerCase());

            if (!hasUppercase) {
                throw new NoUpperAlphaException("The password must contain at least one upper case alphabetic character.");
            }
       // }

        //
        //if (!Character.isLowerCase(first)) {

            boolean hasLowercase = !password.equals(password.toUpperCase());
            if (!hasLowercase) {
                throw new NoLowerAlphaException("The password must contain at least one lower case alphabetic character");
            }
       // }

        boolean hasSpecial = false;
        for(int i = 0; i <password.length(); i++){

            if (password.charAt(i)>32 && password.charAt(i)< 48 || password.charAt(i)>57 && password.charAt(i)< 65 || password.charAt(i)>90 && password.charAt(i)< 97|| password.charAt(i)>123 && password.charAt(i)< 127){
                hasSpecial = true;
            }
        }
        if(!hasSpecial){
            throw new NoSpecialCharacterException("The password must contain at least one special character");
        }




        for (int i = 0; i < password.length() - 2; i++) {

                if ((password.charAt(i) == password.charAt(i + 1)) && (password.charAt(i + 1) == password.charAt(i + 2))) {
                    throw new InvalidSequenceException("The password cannot contain more than two of the same character in sequence.");
                }
            }



        return true;
    }

    /**
     *This method will check if the password is valid and if true determine if it is a weak password
     * @param password
     * @return true or false based on if it is a weak password
     * @throws InvalidSequenceException
     * @throws NoUpperAlphaException
     * @throws NoDigitException
     * @throws NoLowerAlphaException
     * @throws LengthException
     * @throws NoSpecialCharacterException
     */
    public static boolean isWeakPassword(String password) throws InvalidSequenceException, NoUpperAlphaException, NoDigitException, NoLowerAlphaException, LengthException, NoSpecialCharacterException {
        if (isValidPassword(password) && password.length() > 6 && password.length() < 10) {
            return true;
        } else {

            return false;
        }
    }

    /**
     * This method will check an ArrayList of passwords and return an ArrayList with the status of any invalid passwords(weak passwords are not considered invalid)
     * The ArrayList of invalid passwords will be of the following format <password><blank><message of exception thrown>
     * @param password
     * @return the ArrayList of invalid passwords
     */
    public static ArrayList<String> getInvalidPasswords(ArrayList<String> password) {

        IllegalPasswords = new ArrayList<String>();
        String errorMessage = null;


        for (int i = 0; i < password.size(); i++) {
            try {
                isValidPassword(password.get(i));
            } catch (LengthException e) {
                errorMessage = password.get(i) + " - The password must be at least 6 characters long";

                IllegalPasswords.add(errorMessage);
            } catch (NoDigitException e) {
                errorMessage = password.get(i) + " - The password must contain at least one digit";

                IllegalPasswords.add(errorMessage);
            } catch (NoUpperAlphaException e) {
                errorMessage = password.get(i) + " - The password must contain at least one uppercase alphabetic character";

                IllegalPasswords.add(errorMessage);
            } catch (NoLowerAlphaException e) {
                errorMessage = password.get(i) + " - The password must contain at least one lower case alphabetic character";

                IllegalPasswords.add(errorMessage);
            } catch (InvalidSequenceException e) {
                errorMessage = password.get(i) + " - The password cannot contain more than two of the same character in sequence";

                IllegalPasswords.add(errorMessage);
            } catch (NoSpecialCharacterException e) {
                errorMessage = password.get(i) + " - The password must contain at least one special character";

                IllegalPasswords.add(errorMessage);
            }

        }
        return IllegalPasswords;
    }

    /**
     * This method will check if the passwords parameters match, if they do not will throw an exception and print a message
     * @param password
     * @param passwordConfirm
     * @throws UnmatchedException
     */
    public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException {
     if(!password.equals(passwordConfirm)) {
         throw new UnmatchedException("Passwords do not match");
     }
    }

    /**
     * This method will check if the password parameters match and return a boolean variable to represent true/false
     * @param password
     * @param passwordConfirm
     * @return boolean value
     */
    public static boolean comparePasswordsWithReturn(String password,String passwordConfirm){
        return (password.equals(passwordConfirm));
    }

    /**
     * This method will check if the password entered is between 6-9 characters long
     * @param password
     * @return boolean value representing true or false
     */
    public static boolean hasBetweenSixAndNineChars(String password){
        return (password.length()>5 && password.length()<10);
    }

    /**
     * This method will return a boolean value if there is a digit in the passwrod
     * @param password
     * @return boolean value representing true or false
     * @throws NoDigitException
     */
    public static boolean hasDigit(String password) throws NoDigitException {
        char first = password.charAt(0);
        boolean hasDigit = false;
        if(!Character.isDigit(first)) {

            for (int i = 0; i < password.length(); i++) {

                first = password.charAt(i);
                if (Character.isDigit(first)) {
                    hasDigit = true;
                }
                else{
                    throw new NoDigitException("The password must contain at least one digit");
                }
            }
        }

            return hasDigit;

    }

    /**
     * This method will test if the entered password has a lowercase character in it and return true/false based on same
     * @param password
     * @return boolean value
     * @throws NoLowerAlphaException
     */
    public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException {

        char first = password.charAt(0);
        boolean hasLowerCase = false;
        if(!Character.isLowerCase(first)){

            for (int i = 0; i < password.length(); i++) {

                first = password.charAt(i);
                if (Character.isLowerCase(first)) {
                    hasLowerCase = true;
                }
                else{
                    throw new NoLowerAlphaException("The password must contain at least one lower case alphabetic character");
                }
            }
        }

        return hasLowerCase;
    }

    /**
     * this method will check to see if the password more than two of the same characer in a row
     * @param password
     * @return boolean value
     * @throws InvalidSequenceException
     */
    public static boolean hasSameCharInSequence(String password) throws InvalidSequenceException {

        char first = password.charAt(0);
        boolean inSequence = false;

        if (Character.isLowerCase(first) || Character.isUpperCase(first) || Character.isDigit(first)) {

            for (int i = 0; i < password.length() - 2; i++) {

                if ((password.charAt(i) == password.charAt(i + 1)) && (password.charAt(i + 1) == password.charAt(i + 2))) {
                    throw new InvalidSequenceException("The password cannot contain more than two of the same character in sequence");
                }
            }


        }
        return inSequence;
    }

    /**
     * This method will check the entered password to confirm if it has a special character based on ASCII values
     * @param password
     * @return boolean value
     * @throws NoSpecialCharacterException
     */
    public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException {

        boolean hasSpecial = false;
        for(int i = 0; i <password.length(); i++){

            if (password.charAt(i)>32 && password.charAt(i)< 48 || password.charAt(i)>57 && password.charAt(i)< 65 || password.charAt(i)>90 && password.charAt(i)< 97|| password.charAt(i)>123 && password.charAt(i)< 127){
                hasSpecial = true;
            }
        }
        if(!hasSpecial){
            throw new NoSpecialCharacterException("The password must contain at least one special character");
        }

        return hasSpecial;
    }


    /**
     * this method will check if the entered password has an upper case letter
     * @param password
     * @return boolean value
     * @throws NoUpperAlphaException
     */
    public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException {


        boolean hasUppercase = !password.equals(password.toLowerCase());

        if (!hasUppercase) {
            throw new NoUpperAlphaException("The password must contain at least one uppercase alphabetic character");
        }

        return hasUppercase;
    }

    /**
     * This method will check if the password is less than 6 characters long
     * @param password
     * @return return boolean value
     * @throws LengthException
     */
    public static boolean isValidLength(String password) throws LengthException {
        boolean valid = (password.length()>5);
        if(!valid){
            throw new LengthException("The password must be at least 6 characters long");
        }

        return true;
    }

}
