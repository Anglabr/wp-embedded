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
    public PlantCareDataEntry addDataEntryById(Long id, PlantCareDataEntry plantCareDataEntry) {
        PlantCareDevice plantCareDevice = plantCareDeviceRepository.findById(id).orElseThrow(PlantCareDeviceIdException::new);

        plantCareDevice.getData().add(plantCareDataEntry);

        plantCareDataEntryRepository.save(plantCareDataEntry);

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
        plantCareDataEntryRepository.deleteAll(plantCareDevice.getData());

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

    @Override
    public PlantCareDataEntry addDataEntry(Long plantCareDeviceId, PlantCareDataEntry plantCareDataEntry){
       PlantCareDevice plantCareDevice = findById(plantCareDeviceId);

       List<PlantCareDataEntry> list = plantCareDevice.getData();

       list.add(plantCareDataEntry);

       plantCareDeviceRepository.deleteById(plantCareDeviceId);

       plantCareDeviceRepository.save(new PlantCareDevice(list));

       return plantCareDataEntry;
    }
}
