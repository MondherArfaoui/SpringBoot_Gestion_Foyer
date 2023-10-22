package tn.esprit.myfirstproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.myfirstproject.entities.Universite;
import tn.esprit.myfirstproject.services.IUniversiteServices;

import java.util.List;

@RestController
@RequestMapping("/universite")
@RequiredArgsConstructor
public class UniversiteController {

    private final IUniversiteServices universiteService;

    @PostMapping("/add")
    public Universite addUniversite(@RequestBody Universite universite) {
        return universiteService.addUniversite(universite);
    }

    @PutMapping("/update")
    public Universite updateUniversite(@RequestBody Universite universite) {
        return universiteService.updateUniversite(universite);
    }

    @GetMapping("/all")
    public List<Universite> getAllUniversites() {
        return universiteService.getAllUniversites();
    }

    @GetMapping("/{idUniversite}")
    public Universite getUniversiteById(@PathVariable Long idUniversite) {
        return universiteService.getUniversiteById(idUniversite);
    }

    @DeleteMapping("/delete/{idUniversite}")
    public void deleteUniversite(@PathVariable Long idUniversite) {
        universiteService.deleteUniversite(idUniversite);
    }
}
