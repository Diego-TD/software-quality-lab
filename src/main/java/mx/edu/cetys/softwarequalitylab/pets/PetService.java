package mx.edu.cetys.softwarequalitylab.pets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    PetController.PetResponse savePet(PetController.PetRequest petRequest) {
        log.debug("Saving Pet Request: {}", petRequest);


        if (!validatePet(petRequest)){
            return  null; // TODO: handle errors
        }

        Pet newPet = new Pet(petRequest.name(), petRequest.color(), petRequest.race(), petRequest.age());
        Pet savedPet = petRepository.save(newPet);
        log.debug("Saved Pet Response: {}", savedPet);

        return new PetController.PetResponse(savedPet.getId(),  savedPet.getName(), savedPet.getColor(), savedPet.getRace(), savedPet.getAge());
    }


    private boolean validatePet(PetController.PetRequest petRequest) {
        // TODO: validation of pet request

        return true;
    }

    PetController.PetsResponse getAllPets() {
        return new PetController.PetsResponse(petRepository.findAll());
    }

}
