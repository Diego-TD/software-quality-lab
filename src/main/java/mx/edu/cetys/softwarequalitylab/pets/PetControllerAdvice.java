package mx.edu.cetys.softwarequalitylab.pets;

import mx.edu.cetys.softwarequalitylab.commons.ApiResponse;
import mx.edu.cetys.softwarequalitylab.pets.exceptions.InvalidPetDataException;
import mx.edu.cetys.softwarequalitylab.pets.exceptions.PetNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PetControllerAdvice {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Void> handleInvalidPet(InvalidPetDataException e) {
        return new ApiResponse<>("Invalid pet info", null, e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Void> handlePetNotFound(PetNotFoundException e) {
        return new ApiResponse<>("Pet not found", null, e.getMessage());
    }

}
