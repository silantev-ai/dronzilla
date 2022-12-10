package alex.silantev.dronzilla.repository;

import alex.silantev.dronzilla.enums.ErrorCode;
import alex.silantev.dronzilla.exceptions.BizServiceException;
import alex.silantev.dronzilla.models.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Integer> {

    default Drone getEntityOrThrow(int id) {
        return findById(id).orElseThrow(() -> new BizServiceException(ErrorCode.DRONE_NOT_FOUND));
    }

    Optional<Drone> findBySerialNumber(String serialNumber);
}
