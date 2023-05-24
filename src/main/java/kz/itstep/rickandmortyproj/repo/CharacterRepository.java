package kz.itstep.rickandmortyproj.repo;

import kz.itstep.rickandmortyproj.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    List<Character>findAllByName(String name);

}
