package kz.itstep.rickandmortyproj.controller;

import kz.itstep.rickandmortyproj.service.RickAndMortyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class RickAndMortyController {

    private final RickAndMortyService rickAndMortyService;

    public RickAndMortyController(RickAndMortyService rickAndMortyService) {
        this.rickAndMortyService = rickAndMortyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Character> getCharacterById(@PathVariable Long id){
        return null;
    }
}
