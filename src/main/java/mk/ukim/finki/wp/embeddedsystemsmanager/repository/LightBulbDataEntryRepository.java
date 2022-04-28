package mk.ukim.finki.wp.embeddedsystemsmanager.repository;

import mk.ukim.finki.wp.embeddedsystemsmanager.model.data_entry.LightBulbDataEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LightBulbDataEntryRepository extends JpaRepository<LightBulbDataEntry, Long> {
    List<LightBulbDataEntry> findAllByLightBulbDeviceId(Long lightBulbDevice_id);
}
