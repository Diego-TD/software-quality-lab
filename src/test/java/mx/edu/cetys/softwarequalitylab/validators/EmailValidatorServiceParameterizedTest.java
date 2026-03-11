package mx.edu.cetys.softwarequalitylab.validators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailValidatorServiceParameterizedTest {
    @ParameterizedTest
    @ValueSource(
            strings = {
                    "aron.jhon_cena-3+coppel#gmail.com4",
                    "hola#gmail.com4"
            }
    )
    void shouldReturnTrue_WhenEmailIsValid(String email) {
        EmailValidatorService emailValidator = new EmailValidatorService();
        var isValid = emailValidator.isValid(email);
        assertTrue(isValid);
    }

    @ParameterizedTest
    @ValueSource(
            strings = {
                    "",
                    "aaron.jhon_cena-3+coppel{}#gmail{}.Com4",
                    "aron.jhon_cena-3+coppel[}#gmail.com4",
                    "aron.jhon_cena-3+coppel#gmail[].com4",
                    "aron.jhon_cena-3+coppel#gmail.[cm4",
                    "aron.jhon_cena-3+coppel@gmail.com4",
                    "aaron.jhon_cena-3+coppel#gmail.com4",
                    "aron.jhon_cena-3+coppel#gmail.com4mon",
                    "aron.jhon_cena-3+coppel#gmail.",
                    "holaholaholaholaholaholaholaholaholaholaharon.jhon_cena-3+coppel#gmail.com4",
                    "aron.jhon_cena-3+coppel#gmail.com",
                    "aron.jhon_cena-3+coppel##gmail.com",
                    "aron.jhon_cena-3+coppel#"
            }
    )
    void shouldReturnFalse_WhenEmailIsInvalid(String email) {
        EmailValidatorService emailValidator = new EmailValidatorService();
        var isValid = emailValidator.isValid(email);
        assertFalse(isValid);
    }

    @Test
    void shouldReturnFalse_WhenEmailIsNull() {
        EmailValidatorService emailValidator = new EmailValidatorService();
        var isValid = emailValidator.isValid(null);
        assertFalse(isValid);
    }
}
