package mk.ukim.finki.wp.embeddedsystemsmanager.model.data_entry;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Data
public class PlantCareDataEntry {
    public PlantCareDataEntry(Long temperature, Long humidity, Long soilMoisture) {
        this.logTime = LocalDate.now();
        this.temperature = temperature;
        this.humidity = humidity;
        this.soilMoisture = soilMoisture;
    }

    @Id
    @GeneratedValue
    Long id;

    LocalDate logTime;

    Long temperature;

    Long humidity;

    Long soilMoisture;

    public PlantCareDataEntry() {

    }

    @Override
    public String toString() {
        return "PlantCareDataEntry" + id.toString();
    }
}
