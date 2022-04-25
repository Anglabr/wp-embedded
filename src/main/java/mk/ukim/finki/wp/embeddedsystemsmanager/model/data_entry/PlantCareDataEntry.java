package mk.ukim.finki.wp.embeddedsystemsmanager.model.data_entry;

import lombok.Data;
import mk.ukim.finki.wp.embeddedsystemsmanager.model.PlantCareDevice;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Data
public class PlantCareDataEntry {
    public PlantCareDataEntry(Long temperature, Long humidity, Long soilMoisture) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.soilMoisture = soilMoisture;
    }

    @Id
    @GeneratedValue
    long Id;

    LocalDate logTime;

    Long temperature;

    Long humidity;

    Long soilMoisture;

    @ManyToOne
    PlantCareDevice device;

    public PlantCareDataEntry() {

    }
}
