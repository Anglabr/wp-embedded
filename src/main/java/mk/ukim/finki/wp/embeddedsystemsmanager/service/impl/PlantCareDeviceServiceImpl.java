package mk.ukim.finki.wp.embeddedsystemsmanager.service.impl;

import mk.ukim.finki.wp.embeddedsystemsmanager.model.PlantCareDevice;
import mk.ukim.finki.wp.embeddedsystemsmanager.model.data_entry.PlantCareDataEntry;
import mk.ukim.finki.wp.embeddedsystemsmanager.model.exception.PlantCareDeviceIdException;
import mk.ukim.finki.wp.embeddedsystemsmanager.repository.PlantCareDataEntryRepository;
import mk.ukim.finki.wp.embeddedsystemsmanager.repository.PlantCareDeviceRepository;
import mk.ukim.finki.wp.embeddedsystemsmanager.service.PlantCareDeviceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantCareDeviceServiceImpl implements PlantCareDeviceService {

    private final PlantCareDeviceRepository plantCareDeviceRepository;
    private final PlantCareDataEntryRepository plantCareDataEntryRepository;

    public PlantCareDeviceServiceImpl(PlantCareDeviceRepository plantCareDeviceRepository, PlantCareDataEntryRepository plantCareDataEntryRepository) {
        this.plantCareDeviceRepository = plantCareDeviceRepository;
        this.plantCareDataEntryRepository = plantCareDataEntryRepository;
    }

    @Override
    public Optional<PlantCareDataEntry> addDataEntryById(Long id, PlantCareDataEntry plantCareDataEntry) {
        PlantCareDevice plantCareDevice = plantCareDeviceRepository.findById(id).orElseThrow(PlantCareDeviceIdException::new);

        plantCareDevice.getData().add(plantCareDataEntry);

        plantCareDataEntryRepository.save(plantCareDataEntry);

        plantCareDeviceRepository.save(plantCareDevice);

        return Optional.of(plantCareDataEntry);
    }

    @Override
    public List<PlantCareDataEntry> getAllDataEntriesById(Long id) {
        PlantCareDevice plantCareDevice = plantCareDeviceRepository.findById(id).orElseThrow(PlantCareDeviceIdException::new);

        return plantCareDevice.getData();
    }

    @Override
    public List<PlantCareDevice> findAll() {
        return plantCareDeviceRepository.findAll();
    }

    @Override
    public Optional<PlantCareDevice> createPlantCareDevice() {
        PlantCareDevice plantCareDevice = new PlantCareDevice();

        return Optional.of(plantCareDeviceRepository.save(plantCareDevice));
    }

    @Override
    public Optional<PlantCareDevice> deleteById(Long id) {
        PlantCareDevice plantCareDevice = plantCareDeviceRepository.findById(id).orElseThrow(PlantCareDeviceIdException::new);

        plantCareDeviceRepository.deleteById(id);
        plantCareDataEntryRepository.deleteAll(plantCareDevice.getData());

        return Optional.of(plantCareDevice);
    }

    @Override
    public List<PlantCareDevice> deleteAll() {
        List<PlantCareDevice> plantCareDevices = plantCareDeviceRepository.findAll();

        plantCareDeviceRepository.deleteAll();
        plantCareDataEntryRepository.deleteAll();
        return plantCareDevices;
    }

    @Override
    public Optional<PlantCareDevice> findById(Long id) {
        return Optional.of(plantCareDeviceRepository.findById(id).orElseThrow(PlantCareDeviceIdException::new));
    }

}
