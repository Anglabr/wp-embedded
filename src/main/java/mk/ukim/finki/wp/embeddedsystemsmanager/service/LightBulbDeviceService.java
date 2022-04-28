package mk.ukim.finki.wp.embeddedsystemsmanager.service;

import mk.ukim.finki.wp.embeddedsystemsmanager.model.LightBulbDevice;
import mk.ukim.finki.wp.embeddedsystemsmanager.model.data_entry.LightBulbDataEntry;
import mk.ukim.finki.wp.embeddedsystemsmanager.repository.LightBulbDeviceRepository;
import org.springframework.lang.Nullable;

import java.util.List;

public interface LightBulbDeviceService {

    List<LightBulbDevice> findAll();

    List<LightBulbDataEntry> findAllDataForLightBulb(Long lightBulbDeviceId);

    LightBulbDataEntry turnLightBulbOnOff(Long lightBulbDeviceId);

    LightBulbDevice saveLightBulbDevice(@Nullable String location);
}
