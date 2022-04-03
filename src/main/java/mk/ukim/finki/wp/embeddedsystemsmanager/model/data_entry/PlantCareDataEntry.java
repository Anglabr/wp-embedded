package mk.ukim.finki.wp.embeddedsystemsmanager.model.data_entry;

import lombok.Data;
import mk.ukim.finki.wp.embeddedsystemsmanager.model.PlantCareDevice;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Data
public class PlantCareDataEntry {

    @Id
    @GeneratedValue
    long Id;

    LocalDate logTime;

    Float temperature;

    Float humidity;

    Float soilMoisture;

    @ManyToOne
    PlantCareDevice device;
}
