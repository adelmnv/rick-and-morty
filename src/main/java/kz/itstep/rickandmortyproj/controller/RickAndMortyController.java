package kz.itstep.rickandmortyproj.controller;

import kz.itstep.rickandmortyproj.model.Character;
import kz.itstep.rickandmortyproj.repo.CharacterRepository;
import kz.itstep.rickandmortyproj.service.RickAndMortyService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/character")
public class RickAndMortyController {
    private final CharacterRepository characterRepository;
    private final RickAndMortyService rickAndMortyService;

    public RickAndMortyController(RickAndMortyService rickAndMortyService, CharacterRepository characterRepository) {
        this.rickAndMortyService = rickAndMortyService;
        this.characterRepository = characterRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getCharacterById(@PathVariable Long id){
        return ResponseEntity.ok(rickAndMortyService.findCharacterById(id).toString());
    }

    @GetMapping("/")
    public ResponseEntity<List<String>> getCharacterByName(@RequestParam("name") String name){
        return ResponseEntity.ok(rickAndMortyService.findCharacterByName(name));
    }
}
