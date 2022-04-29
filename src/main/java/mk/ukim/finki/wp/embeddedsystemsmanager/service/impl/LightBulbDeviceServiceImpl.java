package mk.ukim.finki.wp.embeddedsystemsmanager.service.impl;

import mk.ukim.finki.wp.embeddedsystemsmanager.model.LightBulbDevice;
import mk.ukim.finki.wp.embeddedsystemsmanager.model.data_entry.LightBulbDataEntry;
import mk.ukim.finki.wp.embeddedsystemsmanager.model.exception.LightBulbDeviceNotFoundException;
import mk.ukim.finki.wp.embeddedsystemsmanager.model.exception.PlantCareDeviceIdException;
import mk.ukim.finki.wp.embeddedsystemsmanager.repository.LightBulbDataEntryRepository;
import mk.ukim.finki.wp.embeddedsystemsmanager.repository.LightBulbDeviceRepository;
import mk.ukim.finki.wp.embeddedsystemsmanager.service.LightBulbDeviceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LightBulbDeviceServiceImpl implements LightBulbDeviceService {

    private final LightBulbDeviceRepository lightBulbDeviceRepository;

    private final LightBulbDataEntryRepository lightBulbDataEntryRepository;

    public LightBulbDeviceServiceImpl(LightBulbDeviceRepository lightBulbDeviceRepository, LightBulbDataEntryRepository lightBulbDataEntryRepository) {
        this.lightBulbDeviceRepository = lightBulbDeviceRepository;
        this.lightBulbDataEntryRepository = lightBulbDataEntryRepository;
    }

    @Override
    public List<LightBulbDevice> findAll() {
        return lightBulbDeviceRepository.findAll();
    }

    @Override
    public List<LightBulbDataEntry> findAllDataForLightBulb(Long lightBulbDeviceId) {
        lightBulbDeviceRepository.findById(lightBulbDeviceId).orElseThrow(LightBulbDeviceNotFoundException::new);
        return lightBulbDataEntryRepository.findAllByLightBulbDeviceId(lightBulbDeviceId);
    }

    @Override
    public Optional<LightBulbDataEntry> turnLightBulbOnOff(Long lightBulbDeviceId) {
        LightBulbDevice lightBulbDevice = lightBulbDeviceRepository.findById(lightBulbDeviceId).orElseThrow(LightBulbDeviceNotFoundException::new);

        Boolean turnedOn = !lightBulbDevice.getTurnedOn();

        lightBulbDevice.setTurnedOn(turnedOn);

        LightBulbDataEntry lightBulbDataEntry = new LightBulbDataEntry(lightBulbDevice, turnedOn);

        lightBulbDataEntryRepository.save(lightBulbDataEntry);

        lightBulbDeviceRepository.save(lightBulbDevice);

        return Optional.of(lightBulbDataEntry);
    }

    @Override
    public Optional<LightBulbDevice> saveLightBulbDevice(String location) {
        return Optional.of(lightBulbDeviceRepository.save(new LightBulbDevice(location == null ? "" : location)));
    }

    @Override
    public LightBulbDevice findLightBulbDeviceById(Long id) {
        return lightBulbDeviceRepository.findById(id).orElseThrow(PlantCareDeviceIdException::new);
    }
}
