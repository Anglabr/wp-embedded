package mk.ukim.finki.wp.embeddedsystemsmanager.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class LightBulbDevice {
    @Id
    @GeneratedValue
    Long id;

    String location;

    Boolean turnedOn = false;

    public LightBulbDevice(String location){
        this.location = location;
    }

    public LightBulbDevice(){}

    @Override
    public String toString() {
        return String.format("%s light bulb", this.location);
    }
}
