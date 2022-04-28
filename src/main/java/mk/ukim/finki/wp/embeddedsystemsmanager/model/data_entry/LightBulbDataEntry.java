package mk.ukim.finki.wp.embeddedsystemsmanager.model.data_entry;

import lombok.Data;
import mk.ukim.finki.wp.embeddedsystemsmanager.model.LightBulbDevice;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class LightBulbDataEntry {
    @Id
    @GeneratedValue
    Long id;

    Boolean turnedOn;
    LocalDateTime logTime;

    @ManyToOne
    LightBulbDevice lightBulbDevice;

    public LightBulbDataEntry(LightBulbDevice lightBulbDevice, Boolean turnedOn) {
        this.lightBulbDevice = lightBulbDevice;
        this.turnedOn = turnedOn;
        this.logTime = LocalDateTime.now();
    }

    public LightBulbDataEntry() {
        this.logTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "turnedOn=" + turnedOn +
                ", logTime=" + logTime ;
    }
}
