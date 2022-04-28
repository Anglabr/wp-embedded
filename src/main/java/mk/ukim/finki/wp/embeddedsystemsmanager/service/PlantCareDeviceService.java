package mk.ukim.finki.wp.embeddedsystemsmanager.service;

import mk.ukim.finki.wp.embeddedsystemsmanager.model.PlantCareDevice;
import mk.ukim.finki.wp.embeddedsystemsmanager.model.data_entry.PlantCareDataEntry;

import java.util.List;

public interface PlantCareDeviceService {

    PlantCareDataEntry addDataEntryById(Long id, Long temperature, Long humidity, Long soilMoisture);

    List<PlantCareDataEntry> getAllDataEntriesById(Long id);

    List<PlantCareDevice> findAll();

    PlantCareDevice createPlantCareDevice();

    PlantCareDevice deleteById(Long id);

    List<PlantCareDevice> deleteAll();

    PlantCareDevice findById(Long id);
}
