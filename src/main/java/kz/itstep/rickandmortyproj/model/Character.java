package kz.itstep.rickandmortyproj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Character {
    @Id
    private Long id;
    private String locationName;
}
