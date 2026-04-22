package mx.edu.cetys.softwarequalitylab.pets;

import mx.edu.cetys.softwarequalitylab.pets.exceptions.InvalidPetDataException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PetControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public PetController.ApiResponse<Void> handleInvalidPet(InvalidPetDataException e) {
        return new PetController.ApiResponse<>("Invalid pet info", null, e.getMessage());
    }


}
