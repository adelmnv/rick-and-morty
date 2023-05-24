package kz.itstep.rickandmortyproj.client;

import lombok.Data;

@Data
public class CharacterResponse {
    private Location location;


    @Data
    public static class Location{
        private String name;
    }
}
