package kz.itstep.rickandmortyproj.client;

import lombok.Data;

@Data
public class CharacterResponse {
    private Location location;
    private String name;
    private String status;
    private String gender;
    private String locationName;
    @Data
    public static class Location{
        private String name;
    }
}
