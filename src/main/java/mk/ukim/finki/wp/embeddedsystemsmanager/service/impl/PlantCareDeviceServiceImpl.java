package mk.ukim.finki.wp.embeddedsystemsmanager.service.impl;

import mk.ukim.finki.wp.embeddedsystemsmanager.model.PlantCareDevice;
import mk.ukim.finki.wp.embeddedsystemsmanager.model.data_entry.PlantCareDataEntry;
import mk.ukim.finki.wp.embeddedsystemsmanager.model.exception.PlantCareDeviceIdException;
import mk.ukim.finki.wp.embeddedsystemsmanager.repository.PlantCareDataEntryRepository;
import mk.ukim.finki.wp.embeddedsystemsmanager.repository.PlantCareDeviceRepository;
import mk.ukim.finki.wp.embeddedsystemsmanager.service.PlantCareDeviceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantCareDeviceServiceImpl implements PlantCareDeviceService {

    private final PlantCareDeviceRepository plantCareDeviceRepository;
    private final PlantCareDataEntryRepository plantCareDataEntryRepository;

    public PlantCareDeviceServiceImpl(PlantCareDeviceRepository plantCareDeviceRepository, PlantCareDataEntryRepository plantCareDataEntryRepository) {
        this.plantCareDeviceRepository = plantCareDeviceRepository;
        this.plantCareDataEntryRepository = plantCareDataEntryRepository;
    }

    @Override
    public PlantCareDataEntry addDataEntryById(Long id, Long temperature, Long humidity, Long soilMoisture) {
        PlantCareDevice plantCareDevice = plantCareDeviceRepository.findById(id).orElseThrow(PlantCareDeviceIdException::new);

        return plantCareDataEntryRepository.save(new PlantCareDataEntry(plantCareDevice, humidity, soilMoisture, temperature));
    }

    @Override
    public List<PlantCareDataEntry> getAllDataEntriesById(Long plantCareDeviceId) {
        return plantCareDataEntryRepository.findAllByPlantCareDeviceId(plantCareDeviceId);
    }

    @Override
    public List<PlantCareDevice> findAll() {
        return plantCareDeviceRepository.findAll();
    }

    @Override
    public PlantCareDevice createPlantCareDevice() {
        PlantCareDevice plantCareDevice = new PlantCareDevice();

        return plantCareDeviceRepository.save(plantCareDevice);
    }

    @Override
    public PlantCareDevice deleteById(Long id) {
        PlantCareDevice plantCareDevice = plantCareDeviceRepository.findById(id).orElseThrow(PlantCareDeviceIdException::new);

        plantCareDeviceRepository.deleteById(id);

        List<PlantCareDataEntry> entries =  getAllDataEntriesById(id);

        plantCareDataEntryRepository.deleteAll(entries);

        return plantCareDevice;
    }

    @Override
    public List<PlantCareDevice> deleteAll() {
        List<PlantCareDevice> plantCareDevices = plantCareDeviceRepository.findAll();

        plantCareDeviceRepository.deleteAll();
        plantCareDataEntryRepository.deleteAll();
        return plantCareDevices;
    }

    @Override
    public PlantCareDevice findById(Long id) {
        return plantCareDeviceRepository.findById(id).orElseThrow(PlantCareDeviceIdException::new);
    }

}
