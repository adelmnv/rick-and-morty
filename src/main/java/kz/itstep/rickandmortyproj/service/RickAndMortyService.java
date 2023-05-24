package kz.itstep.rickandmortyproj.service;

import com.fasterxml.jackson.databind.JsonNode;
import kz.itstep.rickandmortyproj.client.CharacterResponse;
import kz.itstep.rickandmortyproj.model.Character;
import kz.itstep.rickandmortyproj.repo.CharacterRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RickAndMortyService {
    private final WebClient openCharacterClient;
    private final CharacterRepository repo;

    public RickAndMortyService(WebClient openCharacterClient, CharacterRepository characterRepository) {
        this.openCharacterClient = openCharacterClient;
        this.repo = characterRepository;
    }
    private Character fetchCharacterById(Long id){
        CharacterResponse characterResponse = openCharacterClient
                .get().uri(uriBuilder -> uriBuilder
                        .path("character/")
                        .path(String.valueOf(id))
                        .build())
                .retrieve()
                .bodyToMono(CharacterResponse.class)
                .block();

        Character character = new Character();
        if(characterResponse != null){
            character.setLocationName(characterResponse.getLocation().getName());
            character.setGender(characterResponse.getGender());
            character.setName(characterResponse.getName());
            character.setStatus(characterResponse.getStatus());
            character.setId(id);
        }
        return character;
    }

    public Character findCharacterById(Long id){
        return repo.findById(id).orElseGet(() -> {
            Character t = fetchCharacterById(id);
            if (t != null){
                repo.save(t);
            }
            return t;
        });
    }

   private List<Character> fetchCharacterByName(String name){
       JsonNode jsonNode = openCharacterClient.get().uri(uriBuilder -> uriBuilder
                       .path("character")
                       .queryParam("name", name)
                       .build())
               .retrieve()
               .bodyToMono(JsonNode.class)
               .block();

       List<Character> characters = new ArrayList<>();
       if(jsonNode!= null){
           int s = jsonNode.get("results").size();
           for(int i=0;i<s;i++){
               Character character = new Character();
               character.setId(jsonNode.get("results").get(i).get("id").asLong());
               character.setName(jsonNode.get("results").get(i).get("name").asText());
               character.setGender(jsonNode.get("results").get(i).get("gender").asText());
               character.setStatus(jsonNode.get("results").get(i).get("status").asText());
               character.setLocationName(jsonNode.get("results").get(i).get("location").get("name").asText());

               characters.add(character);
               /*if(!repo.existsById(character.getId()))
                   repo.save(character);*/
           }


       }
       return characters;
    }

    public List<String> findCharacterByName(String name){
        List<Character> list = repo.findAllByName(name);
        if(list.size() != countCharacterByName(name)){
           list = fetchCharacterByName(name);
        }
        List<String> string = new ArrayList<>();
        for(Character x : list){
            string.add(x.toString());
        }
        if(string.isEmpty())
            string.add("No content");
        return string;
    }

    public int countCharacterByName(String name){
        try{
            JsonNode jsonNode = openCharacterClient.get().uri(uriBuilder -> uriBuilder
                            .path("character")
                            .queryParam("name", name)
                            .build())
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .block();
            if(jsonNode!= null) {
                int size = jsonNode.get("info").get("count").asInt();
                return size;
            }
        }
        catch(Exception er){
            return 0;
        }
        return 0;
    }
}
