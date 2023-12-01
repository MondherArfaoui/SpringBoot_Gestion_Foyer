package tn.esprit.myfirstproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.myfirstproject.entities.Bloc;
import tn.esprit.myfirstproject.services.IBlocServices;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/bloc")
@RequiredArgsConstructor
public class BlocController {

    private final IBlocServices blocService;

    @PostMapping("/add")
    public Bloc addBloc(@RequestBody Bloc bloc) {
        return blocService.addBloc(bloc);
    }

    @PutMapping("/update")
    public Bloc updateBloc(@RequestBody Bloc bloc) {
        return blocService.updateBloc(bloc);
    }

    @GetMapping("/getAllBlocsByIdFoyer/{idFoyer}")
    public List<Bloc> getAllBlocsByIdFoyer(@PathVariable Long idFoyer) {
        return blocService.getAllBlocsByIdFoyer(idFoyer);
    }

    @GetMapping("/all")
    public List<Bloc> getAllBlocs() {
        return blocService.getAllBlocs();
    }

    @GetMapping("/{idBloc}")
    public Bloc getBlocById(@PathVariable Long idBloc) {
        return blocService.getBlocById(idBloc);
    }

    @DeleteMapping("/delete/{idBloc}")
    public void deleteBloc(@PathVariable Long idBloc) {
        blocService.deleteBloc(idBloc);
    }


    @PutMapping("/affecterChambres/{idBloc}")
    public Bloc affecterChambresABloc(@RequestBody List<Long> idChambre, @PathVariable Long idBloc) {
        return blocService.affecterChambresABloc(idChambre, idBloc);
    }
    @PutMapping("/affecterBlocFoyer/{idBloc}/{idFoyer}")
    public Bloc affecterBlocAFoyer(@PathVariable Long idBloc, @PathVariable Long idFoyer) {
        return blocService.affecterBlocAFoyer(idBloc, idFoyer);
    }
}
