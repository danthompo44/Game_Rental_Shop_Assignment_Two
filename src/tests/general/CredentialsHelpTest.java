package tests.general;

import GameApp.java.general.CredentialsHelp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CredentialsHelpTest {
    String correctUser = "dan";
    String correctPass = "dan";
    String incorrectUser = "dave";
    String incorrectPass = "password";

    @Test
    void credentialsBooleanReturnsTrueWhenCredentialsAreCorrect(){
        Assertions.assertTrue(CredentialsHelp.checkCredentials(correctUser, correctPass));
    }
    @Test
    void credentialsBooleanReturnsFalseWhenBothUserNameAndPasswordAreIncorrect(){
        Assertions.assertFalse(CredentialsHelp.checkCredentials(incorrectUser, incorrectPass));
    }
    @Test
    void credentialsBooleanReturnsFalseWhenPasswordIsIncorrectButUsernameIsCorrect(){
        Assertions.assertFalse(CredentialsHelp.checkCredentials(correctUser, incorrectPass));
    }
    @Test
    void credentialsBooleanReturnsFalseWhenCorrectPasswordIsEnteredButUsernameIsIncorrect(){
        Assertions.assertFalse(CredentialsHelp.checkCredentials(incorrectUser, correctPass));
    }
}
