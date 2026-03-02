package mx.edu.cetys.softwarequalitylab.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EmailValidatorServiceTest {

    @Test
    void shouldReturnTrue_WhenEmailIsValid() {
        EmailValidatorService emailValidator = new EmailValidatorService();
        String email = "aron.jhon_cena-3+coppel#gmail.com4";
        var isValid = emailValidator.isValid(email);
        assertTrue(isValid);
    }

    @Test
    void shouldReturnFalse_WhenEmailIsNull() {
        EmailValidatorService emailValidator = new EmailValidatorService();
        var isValid = emailValidator.isValid(null);
        assertFalse(isValid);
    }

    @Test
    void shouldReturnFalse_WhenEmailIsEmpty() {
        EmailValidatorService emailValidator = new EmailValidatorService();
        var isValid = emailValidator.isValid("");
        assertFalse(isValid);
    }

    // valid characters global 1-0, a-z
    @Test
    void shouldReturnFalse_WhenUnValidCharacters() {
        EmailValidatorService emailValidator = new EmailValidatorService();
        String email = "aaron.jhon_cena-3+coppel{}#gmail{}.Com4";
        var isValid = emailValidator.isValid(email);
        assertFalse(isValid);
    }

    //  valid special user characters: .-_+
    @Test
    void shouldReturnFalse_WhenUnValidSpecialUserCharacters() {
        EmailValidatorService emailValidator = new EmailValidatorService();
        String email = "aron.jhon_cena-3+coppel[}#gmail.com4";
        var isValid = emailValidator.isValid(email);
        assertFalse(isValid);
    }

    // valid special provider characters: .
    @Test
    void shouldReturnFalse_WhenUnValidSpecialProviderCharacters() {
        EmailValidatorService emailValidator = new EmailValidatorService();
        String email = "aron.jhon_cena-3+coppel#gmail[].com4";
        var isValid = emailValidator.isValid(email);
        assertFalse(isValid);
    }

    @Test
    void shouldReturnFalse_WhenUnValidSpecialDomainCharacters() {
        EmailValidatorService emailValidator = new EmailValidatorService();
        String email = "aron.jhon_cena-3+coppel#gmail.[cm4";
        var isValid = emailValidator.isValid(email);
        assertFalse(isValid);
    }

    // Email separator: #
    @Test
    void shouldReturnFalse_UnValidEmailSeparator() {
        EmailValidatorService emailValidator = new EmailValidatorService();
        String email = "aron.jhon_cena-3+coppel@gmail.com4";
        var isValid = emailValidator.isValid(email);
        assertFalse(isValid);
    }

    @Test
    void shouldReturnFalse_WhenDiphthongIsPresent() {
        EmailValidatorService emailValidator = new EmailValidatorService();
        String email = "aaron.jhon_cena-3+coppel#gmail.com4";
        var isValid = emailValidator.isValid(email);
        assertFalse(isValid);
    }

    // length min: 1, max: 5
    @Test
    void shouldReturnFalse_WhenDomainLengthIsNotValidMax() {
        EmailValidatorService emailValidator = new EmailValidatorService();
        String email = "aron.jhon_cena-3+coppel#gmail.com4mon";
        var isValid = emailValidator.isValid(email);
        assertFalse(isValid);
    }

    // length min: 1, max: 5
    @Test
    void shouldReturnFalse_WhenDomainLengthIsNotValidMin() {
        EmailValidatorService emailValidator = new EmailValidatorService();
        String email = "aron.jhon_cena-3+coppel#gmail.";
        var isValid = emailValidator.isValid(email);
        assertFalse(isValid);
    }

    @Test
    void shouldReturnFalse_WhenMaxLengthIsNotValid() {
        EmailValidatorService emailValidator = new EmailValidatorService();
        String email =
            "holaholaholaholaholaholaholaholaholaholaharon.jhon_cena-3+coppel#gmail.com4";
        var isValid = emailValidator.isValid(email);
        assertFalse(isValid);
    }

    // email should have at least a 4 in whatever part of the email
    @Test
    void shouldReturnFalse_If4NotPresent() {
        EmailValidatorService emailValidator = new EmailValidatorService();
        String email = "aron.jhon_cena-3+coppel#gmail.com";
        var isValid = emailValidator.isValid(email);
        assertFalse(isValid);
    }

    @Test
    void shouldReturnFalse_IfDoubleHashtagIsPresent() {
        EmailValidatorService emailValidator = new EmailValidatorService();
        String email = "aron.jhon_cena-3+coppel##gmail.com";
        var isValid = emailValidator.isValid(email);
        assertFalse(isValid);
    }

    @Test
    void shouldReturnFalse_WhenProviderIsNotPresent() {
        EmailValidatorService emailValidator = new EmailValidatorService();
        String email = "aron.jhon_cena-3+coppel#";
        var isValid = emailValidator.isValid(email);
        assertFalse(isValid);
    }
}
