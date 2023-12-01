package tn.esprit.myfirstproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.myfirstproject.entities.Foyer;
import tn.esprit.myfirstproject.services.IFoyerServices;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/foyer")
@RequiredArgsConstructor
public class FoyerController {

    private final IFoyerServices foyerService;

    @PostMapping("/add")
    public Foyer addFoyer(@RequestBody Foyer foyer) {
        return foyerService.ajouterFoyer(foyer);
    }

    @PostMapping("/add/{idUniversite}")
    public Foyer ajouterFoyerEtAffecterAUniversite(@RequestBody Foyer foyer, @PathVariable Long idUniversite) {
        return foyerService.ajouterFoyerEtAffecterAUniversite(foyer, idUniversite);
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

    @GetMapping("/getFoyerByIdEtudiant/{idEtudiant}")
    public Foyer getFoyerByIdEtudiant(@PathVariable Long idEtudiant) {
        return foyerService.getFoyerByIdEtudiant(idEtudiant);
    }

    @DeleteMapping("/delete/{idFoyer}")
    public void deleteFoyer(@PathVariable Long idFoyer) {
        foyerService.deleteFoyer(idFoyer);
    }
}
