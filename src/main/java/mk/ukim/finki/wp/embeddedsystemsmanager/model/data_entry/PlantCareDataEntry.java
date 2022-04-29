package mk.ukim.finki.wp.embeddedsystemsmanager.model.data_entry;

import lombok.Data;
import mk.ukim.finki.wp.embeddedsystemsmanager.model.PlantCareDevice;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Entity
@Data
public class PlantCareDataEntry {
    public PlantCareDataEntry(PlantCareDevice plantCareDevice, Long temperature, Long humidity, Long soilMoisture) {
        this.plantCareDevice = plantCareDevice;
        this.logTime = LocalDate.now();
        this.temperature = temperature;
        this.humidity = humidity;
        this.soilMoisture = soilMoisture;
    }

    @Id
    @GeneratedValue
    Long id;

    @ManyToOne
    PlantCareDevice plantCareDevice;

    LocalDate logTime;

    Long temperature;

    Long humidity;

    Long soilMoisture;

    public PlantCareDataEntry() {

    }

    @Override
    public String toString() {
        return String.format("%d.%d.%d %d %d %d", logTime.getDayOfMonth(), logTime.getMonthValue(), logTime.getYear(), temperature, humidity, soilMoisture);
    }
}
