package mk.ukim.finki.wp.embeddedsystemsmanager.service;

import mk.ukim.finki.wp.embeddedsystemsmanager.model.LightBulbDevice;
import mk.ukim.finki.wp.embeddedsystemsmanager.model.data_entry.LightBulbDataEntry;
import mk.ukim.finki.wp.embeddedsystemsmanager.repository.LightBulbDeviceRepository;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;

public interface LightBulbDeviceService {

    List<LightBulbDevice> findAll();

    List<LightBulbDataEntry> findAllDataForLightBulb(Long lightBulbDeviceId);

    Optional<LightBulbDataEntry> turnLightBulbOnOff(Long lightBulbDeviceId);

    Optional<LightBulbDevice> saveLightBulbDevice(@Nullable String location);
}
