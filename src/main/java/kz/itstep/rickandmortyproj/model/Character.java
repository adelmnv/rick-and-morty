package kz.itstep.rickandmortyproj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Character {
    @Id
    private Long id;
    private String name;
    private String status;
    private String gender;
    private String locationName;
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("id: ").append(getId());
        sb.append("  name: ").append(getName());
        sb.append("  gender: ").append(getGender());
        sb.append("  status: ").append(getStatus());
        sb.append("  location name: ").append(getLocationName()).append("  ");
        return sb.toString();
    }
}
