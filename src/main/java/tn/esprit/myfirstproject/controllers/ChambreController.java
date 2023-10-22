package tn.esprit.myfirstproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.myfirstproject.entities.Chambre;
import tn.esprit.myfirstproject.services.IChambreServices;

import java.util.List;

@RestController
@RequestMapping("/chambre")
@RequiredArgsConstructor
public class ChambreController {

    private final IChambreServices chambreService;

    @PostMapping("/add")
    public Chambre addChambre(@RequestBody Chambre chambre) {
        return chambreService.addChambre(chambre);
    }

    @PutMapping("/update")
    public Chambre updateChambre(@RequestBody Chambre chambre) {
        return chambreService.updateChambre(chambre);
    }

    @GetMapping("/all")
    public List<Chambre> getAllChambres() {
        return chambreService.getAllChambres();
    }

    @GetMapping("/{idChambre}")
    public Chambre getChambreById(@PathVariable Long idChambre) {
        return chambreService.getChambreById(idChambre);
    }

    @DeleteMapping("/delete/{idChambre}")
    public void deleteChambre(@PathVariable Long idChambre) {
        chambreService.deleteChambre(idChambre);
    }
}
