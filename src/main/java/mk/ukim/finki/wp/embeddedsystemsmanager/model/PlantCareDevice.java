package mk.ukim.finki.wp.embeddedsystemsmanager.model;

import lombok.Data;
import mk.ukim.finki.wp.embeddedsystemsmanager.model.data_entry.PlantCareDataEntry;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class PlantCareDevice {

    @GeneratedValue
    @Id
    Long id;


    @Override
    public String toString() {
        return "PlantCareDevice" + id.toString();
    }
}
