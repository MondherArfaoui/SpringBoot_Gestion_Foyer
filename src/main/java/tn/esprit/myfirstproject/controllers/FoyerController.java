package tn.esprit.myfirstproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.myfirstproject.entities.Foyer;
import tn.esprit.myfirstproject.services.IFoyerServices;

import java.util.List;

@RestController
@RequestMapping("/foyer")
@RequiredArgsConstructor
public class FoyerController {

    private final IFoyerServices foyerService;

    @PostMapping("/add")
    public Foyer addFoyer(@RequestBody Foyer foyer) {
        return foyerService.addFoyer(foyer);
    }

    @PutMapping("/update")
    public Foyer updateFoyer(@RequestBody Foyer foyer) {
        return foyerService.updateFoyer(foyer);
    }

    @GetMapping("/all")
    public List<Foyer> getAllFoyers() {
        return foyerService.getAllFoyers();
    }

    @GetMapping("/{idFoyer}")
    public Foyer getFoyerById(@PathVariable Long idFoyer) {
        return foyerService.getFoyerById(idFoyer);
    }

    @DeleteMapping("/delete/{idFoyer}")
    public void deleteFoyer(@PathVariable Long idFoyer) {
        foyerService.deleteFoyer(idFoyer);
    }
}
