import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author
 *
 */
public class PasswordCheckerTest_STUDENT_Test {

    ArrayList<String> invalidPasswordsArray;
    ArrayList<String> validPasswordsArray;
    String password = "Robert";
    String passwordConfirm = "robert";
    String allCaps = "ROBERT";
    String withDigit = "Robert8";
    String withDuplicate = "YYYYhello!0";
    String between6And9Chars = "Thankyou";
    String longPassword = "Thankyousomuch!";


    @Before
    public void setUp() throws Exception {

        String[] containsInvalidPwd={"12Hello1", "MyNameJef", "PresidentObama", "JunkieXL"};
        invalidPasswordsArray = new ArrayList<String>();
        invalidPasswordsArray.addAll(Arrays.asList(containsInvalidPwd));

        String[] allValidPasswords = {"TonyStank3000#","TrickySkunk4&"};
        validPasswordsArray = new ArrayList<String>();
        validPasswordsArray.addAll(Arrays.asList(allValidPasswords));


    }

    @After
    public void tearDown() throws Exception {
        invalidPasswordsArray = null;
        validPasswordsArray = null;

    }

    /**
     * Test if the password is less than 8 characters long.
     * This test should throw a LengthException for second case.
     */



    @Test
    public void testIsValidPasswordTooShort()
    {
       try{
           assertTrue(PasswordCheckerUtility.isValidLength(password));
       }
       catch(LengthException e){
           e.printStackTrace();
       }
    }

    /**
     * Test if the password has at least one uppercase alpha character
     * This test should throw a NoUpperAlphaException for second case
     */
    @Test
    public void testIsValidPasswordNoUpperAlpha()
    {
        try{
            assertTrue(PasswordCheckerUtility.hasUpperAlpha(longPassword));
        }
        catch(NoUpperAlphaException e){
            e.printStackTrace();
    }
    }

    /**
     * Test if the password has at least one lowercase alpha character
     * This test should throw a NoLowerAlphaException for second case
     */
    @Test
    public void testIsValidPasswordNoLowerAlpha()
    {
       try{
           assertFalse(PasswordCheckerUtility.hasLowerAlpha(allCaps));
       }
       catch (NoLowerAlphaException e){
           e.printStackTrace();
    }
    }
    /**
     * Test if the password has more than 2 of the same character in sequence
     * This test should throw a InvalidSequenceException for second case
     */
    @Test
    public void testIsWeakPassword()
    {
        try{
            assertTrue(PasswordCheckerUtility.isValidPassword(longPassword));
        }
        catch(Exception e){
            e.printStackTrace();
    }

    }

    /**
     * Test if the password has more than 2 of the same character in sequence
     * This test should throw a InvalidSequenceException for second case
     */
    @Test
    public void testIsValidPasswordInvalidSequence()
    {
      try{
          assertTrue(PasswordCheckerUtility.hasSameCharInSequence(withDuplicate));
      }
      catch(InvalidSequenceException e){
        e.printStackTrace();
    }
    }

    /**
     * Test if the password has at least one digit
     * One test should throw a NoDigitException
     */
    @Test
    public void testIsValidPasswordNoDigit()
    {
       try{
           assertFalse(PasswordCheckerUtility.hasDigit(passwordConfirm));
           assertTrue(PasswordCheckerUtility.hasDigit(withDigit));
       }
       catch (NoDigitException e){
           e.printStackTrace();
    }
    }

    /**
     * Test correct passwords
     * This test should not throw an exception
     */
    @Test
    public void testIsValidPasswordSuccessful()
    {
        ArrayList<String> results;
        results = PasswordCheckerUtility.getInvalidPasswords(validPasswordsArray);
       try{
           assertTrue(PasswordCheckerUtility.isValidPassword(validPasswordsArray.get(0)));
           assertTrue(PasswordCheckerUtility.isValidPassword(validPasswordsArray.get(1)));
       } catch (NoLowerAlphaException e) {
           e.printStackTrace();
       } catch (LengthException e) {
           e.printStackTrace();
       } catch (NoDigitException e) {
           e.printStackTrace();
       } catch (NoUpperAlphaException e) {
           e.printStackTrace();
       } catch (NoSpecialCharacterException e) {
           e.printStackTrace();
       } catch (InvalidSequenceException e) {
           e.printStackTrace();
       }


    }

    /**
     * Test the invalidPasswords method
     * Check the results of the ArrayList of Strings returned by the validPasswords method
     */
    @Test
    public void testInvalidPasswords() {

        //{"12Hello1", "MyNameJef", "PresidentObama", "JunkieXL"};
        ArrayList<String> results;
        results = PasswordCheckerUtility.getInvalidPasswords(invalidPasswordsArray);
        assertEquals(4, results.size());
        assertEquals("12Hello1 - The password must contain at least one special character", results.get(0));
        assertEquals("MyNameJef - The password must contain at least one digit"		, results.get(1));
        assertEquals("PresidentObama - The password must contain at least one digit"		, results.get(2));
        assertEquals("JunkieXL - The password must contain at least one digit"				, results.get(3));

    }

}