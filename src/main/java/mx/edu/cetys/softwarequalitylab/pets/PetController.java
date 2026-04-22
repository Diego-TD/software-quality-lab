package mx.edu.cetys.softwarequalitylab.pets;

import mx.edu.cetys.softwarequalitylab.commons.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    // DTOs for Request and responses
    record PetRequest(String name, String color, String race, Integer age){}
    record PetResponse(Long id,String name, String color, String race, Integer age) {}
    record PetsResponse(List<Pet> pets) {}
    public record PetWrapper(PetResponse pet){}

    @GetMapping("/help")
    ApiResponse<PetResponse> help() {
        return new ApiResponse<>("This is the help endpoint", null, null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ApiResponse<PetWrapper> createPet(@RequestBody PetRequest petRequest) {
        return new ApiResponse<>("New Pet was added", new PetWrapper(petService.savePet(petRequest)) , null);
    }

    // TODO: pagination
    @GetMapping
    ApiResponse<PetsResponse> getPets() {
        return new ApiResponse<>("List of all pets", petService.getAllPets(), null);
    }

    @GetMapping("/{petId}")
    @ResponseStatus(HttpStatus.OK)
     ApiResponse<PetWrapper> findPetById(@PathVariable Long petId) {
        var pet = petService.getPetById(petId);
        return new ApiResponse<>("Pet found", new PetWrapper(pet), null);
     }

    // PUT localhost:8080/pets/{id} -- Update pet by id
    // DELETE localhost:8080/pets/{id} -- Flag available: yes/no
    // not found
}
