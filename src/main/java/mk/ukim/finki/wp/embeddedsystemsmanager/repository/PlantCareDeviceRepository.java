package mk.ukim.finki.wp.embeddedsystemsmanager.repository;

import mk.ukim.finki.wp.embeddedsystemsmanager.model.PlantCareDevice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlantCareDeviceRepository extends JpaRepository<PlantCareDevice, Long> {
    @Override
    List<PlantCareDevice> findAll();
}
