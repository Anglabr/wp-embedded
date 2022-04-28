package mk.ukim.finki.wp.embeddedsystemsmanager.repository;

import mk.ukim.finki.wp.embeddedsystemsmanager.model.data_entry.PlantCareDataEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlantCareDataEntryRepository extends JpaRepository<PlantCareDataEntry, Long> {
    List<PlantCareDataEntry> findAllByPlantCareDeviceId(Long plantCareDevice_id);
}
