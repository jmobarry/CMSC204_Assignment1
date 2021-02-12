import static org.junit.jupiter.api.Assertions.assertEquals;
        import static org.junit.jupiter.api.Assertions.assertFalse;
        import static org.junit.jupiter.api.Assertions.assertThrows;
        import static org.junit.jupiter.api.Assertions.assertTrue;

        import java.util.ArrayList;
        import java.util.Arrays;

        import org.junit.jupiter.api.AfterEach;
        import org.junit.jupiter.api.Assertions;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import org.junit.jupiter.api.function.Executable;

public class PasswordCheckerTestPrivate {
    ArrayList<String> invalidPasswordsArray;
    ArrayList<String> validPasswordsArray;
    String password = "Hello";
    String passwordConfirm = "hello";
    String allCaps = "HELLO";
    String withDigit = "Hello6";
    String withSpecialChar = "Hello!";
    String withNoDuplicate = "GoodBye!";
    String between6And9Chars = "JavaisFun";
    String longPassword = "JavaisFunveryfun";

    @BeforeEach
    void setUp() throws Exception {
        String[] containsInvalidPwd = {"334455BB", "Im2cool4U", "george2ZZZ", "4sale", "bertha22", "4wardMarch",
                "august30", "Applesxx", "aa11b", "pilotProject", "myPassword",
                "myPassword2"};
        invalidPasswordsArray = new ArrayList<String>();
        invalidPasswordsArray.addAll(Arrays.asList(containsInvalidPwd));

        String[] allValidPasswords = {"a1A#b1Bc1Cd1D", "Hello@123", "4heB#rex7", "4saleHe!", "myPassword2%"};
        validPasswordsArray = new ArrayList<String>();
        validPasswordsArray.addAll(Arrays.asList(allValidPasswords));
    }

    @AfterEach
    void tearDown() throws Exception {
        invalidPasswordsArray = null;
        validPasswordsArray= null;
    }

    @Test
    void testComparePasswords() {
        Throwable exception = assertThrows(UnmatchedException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                PasswordCheckerUtility.comparePasswords(password, passwordConfirm);
            }
        });

        assertEquals("Passwords do not match", exception.getMessage());
    }

    @Test
    void testComparePasswordsWithReturn() {
        assertFalse(PasswordCheckerUtility.comparePasswordsWithReturn(password, passwordConfirm));
        assertTrue(PasswordCheckerUtility.comparePasswordsWithReturn(password, password));
    }

    @Test
    void testValidLengthValid() {
        try {
            assertTrue(PasswordCheckerUtility.isValidLength("Beautiful"));
        } catch (LengthException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testInValidLength() {
        Throwable exception = Assertions.assertThrows(LengthException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                PasswordCheckerUtility.isValidLength(password);
            }
        });
        assertEquals("The password must be at least 6 characters long", exception.getMessage());
    }

    @Test
    void testHasUpperAlphaValid() {
        try {
            assertTrue(PasswordCheckerUtility.hasUpperAlpha("Beautiful"));
        } catch (NoUpperAlphaException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDoesNotHaveUpperAlpha() {
        Throwable exception = assertThrows(NoUpperAlphaException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                PasswordCheckerUtility.hasUpperAlpha(passwordConfirm);
            }
        });
        assertEquals("The password must contain at least one uppercase alphabetic character", exception.getMessage());
    }

    @Test
    public void testHasLowerAlpha() {
        try {
            assertTrue(PasswordCheckerUtility.hasLowerAlpha(password));
        } catch (NoLowerAlphaException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDoesNotHaveLowerAlpha() {
        Throwable exception = assertThrows(NoLowerAlphaException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                PasswordCheckerUtility.hasLowerAlpha(allCaps);
            }
        });
        assertEquals("The password must contain at least one lower case alphabetic character", exception.getMessage());

    }

    @Test
    public void testHasDigit() {
        try {
            assertTrue(PasswordCheckerUtility.hasDigit(withDigit));
        } catch (NoDigitException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDoesNotHaveDigit() {
        Throwable exception = assertThrows(NoDigitException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                PasswordCheckerUtility.hasDigit(password);
            }
        });
        assertEquals("The password must contain at least one digit", exception.getMessage());
    }

    @Test
    public void testHasSpecialChar() {
        try {
            assertTrue(PasswordCheckerUtility.hasSpecialChar(withSpecialChar));
        } catch (NoSpecialCharacterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDoesNotHaveSpecialChar() {
        Throwable exception = assertThrows(NoSpecialCharacterException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                PasswordCheckerUtility.hasSpecialChar(password);
            }
        });
        assertEquals("The password must contain at least one special character", exception.getMessage());
    }

    @Test
    public void testHasSameCharInSequence() {
        Throwable exception = assertThrows(InvalidSequenceException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                PasswordCheckerUtility.hasSameCharInSequence("AAAbb@123");
            }
        });
        assertEquals("The password cannot contain more than two of the same character in sequence", exception.getMessage());
    }

    @Test
    public void testDoesNotHaveSameCharInSequence() {
        try {
            assertTrue(PasswordCheckerUtility.hasSpecialChar(withNoDuplicate));
        } catch (NoSpecialCharacterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testHasBetweenSixAndNineChars() {
        assertTrue(PasswordCheckerUtility.hasBetweenSixAndNineChars(between6And9Chars));
        assertTrue(PasswordCheckerUtility.hasBetweenSixAndNineChars(withSpecialChar));
        assertFalse(PasswordCheckerUtility.hasBetweenSixAndNineChars(longPassword));
    }

    @Test
    public void testGetInvalidPasswords() {
        ArrayList<String> results;
        results = PasswordCheckerUtility.getInvalidPasswords(invalidPasswordsArray);
        assertEquals(12, results.size());
        assertEquals("334455BB - The password must contain at least one lower case alphabetic character", results.get(0));
        assertEquals("Im2cool4U - The password must contain at least one special character"		, results.get(1));
        assertEquals("george2ZZZ - The password must contain at least one special character"		, results.get(2));
        assertEquals("4sale - The password must be at least 6 characters long"				, results.get(3));
        assertEquals("bertha22 - The password must contain at least one uppercase alphabetic character"	, results.get(4));
        assertEquals("4wardMarch - The password must contain at least one special character"		, results.get(5));
        assertEquals("august30 - The password must contain at least one uppercase alphabetic character"	, results.get(6));
        assertEquals("Applesxx - The password must contain at least one digit"				, results.get(7));
        assertEquals("aa11b - The password must be at least 6 characters long"				, results.get(8));
        assertEquals("pilotProject - The password must contain at least one digit"			, results.get(9));
        assertEquals("myPassword - The password must contain at least one digit"			, results.get(10));
        assertEquals("myPassword2 - The password must contain at least one special character"		, results.get(11));
    }

    @Test
    public void testGetValidPasswords() {
        ArrayList<String> results;
        results = PasswordCheckerUtility.getInvalidPasswords(validPasswordsArray);
        System.out.println(validPasswordsArray);
        assertTrue(results.isEmpty());
    }

}