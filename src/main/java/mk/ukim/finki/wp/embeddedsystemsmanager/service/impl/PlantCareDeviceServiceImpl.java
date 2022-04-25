package mk.ukim.finki.wp.embeddedsystemsmanager.service.impl;

import mk.ukim.finki.wp.embeddedsystemsmanager.model.PlantCareDevice;
import mk.ukim.finki.wp.embeddedsystemsmanager.model.data_entry.PlantCareDataEntry;
import mk.ukim.finki.wp.embeddedsystemsmanager.model.exception.PlantCareDeviceIdException;
import mk.ukim.finki.wp.embeddedsystemsmanager.repository.PlantCareDeviceRepository;
import mk.ukim.finki.wp.embeddedsystemsmanager.service.PlantCareDeviceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantCareDeviceServiceImpl implements PlantCareDeviceService {

    private final PlantCareDeviceRepository plantCareDeviceRepository;

    public PlantCareDeviceServiceImpl(PlantCareDeviceRepository plantCareDeviceRepository) {
        this.plantCareDeviceRepository = plantCareDeviceRepository;
    }

    @Override
    public PlantCareDataEntry addDataEntryById(Long id, PlantCareDataEntry plantCareDataEntry) {
        PlantCareDevice plantCareDevice = plantCareDeviceRepository.findById(id).orElseThrow(PlantCareDeviceIdException::new);

        plantCareDevice.getData().add(plantCareDataEntry);

        plantCareDeviceRepository.save(plantCareDevice);

        return plantCareDataEntry;
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
    public PlantCareDevice createPlantCareDevice() {
        PlantCareDevice plantCareDevice = new PlantCareDevice();

        return plantCareDeviceRepository.save(plantCareDevice);
    }

    @Override
    public PlantCareDevice deleteById(Long id) {
        PlantCareDevice plantCareDevice = plantCareDeviceRepository.findById(id).orElseThrow(PlantCareDeviceIdException::new);

        plantCareDeviceRepository.deleteById(id);

        return plantCareDevice;
    }

    @Override
    public List<PlantCareDevice> deleteAll() {
        List<PlantCareDevice> plantCareDevices = plantCareDeviceRepository.findAll();

        plantCareDeviceRepository.deleteAll();

        return plantCareDevices;
    }

}
