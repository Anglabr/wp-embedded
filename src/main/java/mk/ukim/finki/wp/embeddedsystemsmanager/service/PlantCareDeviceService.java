package mk.ukim.finki.wp.embeddedsystemsmanager.service;

import mk.ukim.finki.wp.embeddedsystemsmanager.model.PlantCareDevice;
import mk.ukim.finki.wp.embeddedsystemsmanager.model.data_entry.PlantCareDataEntry;

import java.util.List;
import java.util.Optional;

public interface PlantCareDeviceService {

    Optional<PlantCareDataEntry> addDataEntryById(Long id, Long temperature, Long humidity, Long soilMoisture);

    List<PlantCareDataEntry> getAllDataEntriesById(Long id);

    List<PlantCareDevice> findAll();

    Optional<PlantCareDevice> createPlantCareDevice();

    Optional<PlantCareDevice> deleteById(Long id);

    List<PlantCareDevice> deleteAll();

    Optional<PlantCareDevice> findById(Long id);

}
