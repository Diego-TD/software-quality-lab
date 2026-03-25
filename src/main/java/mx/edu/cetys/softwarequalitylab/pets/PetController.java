package mx.edu.cetys.softwarequalitylab.pets;

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
    record ApiResponse<T>(String info, T response, String error){}
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

    @GetMapping
    ApiResponse<PetsResponse> getPets() {
        return new ApiResponse<>("List of all pets", petService.getAllPets(), null);
    }

    // GET localhost:8080/pets -- TODOS los pets, TODO: pagination
    // GET localhost:8080/pets/{id} -- pet by id
    // POST localhost:8080/pets -- New pet with RequestBody {json body} - DTO/Record/POJO
    // PUT localhost:8080/pets/{id} -- Update pet by id
    // DELETE localhost:8080/pets/{id} -- Flag available: yes/no

}
