package alex.silantev.dronzilla.repository;

import alex.silantev.dronzilla.models.DroneBatteryEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneBatteryEventRepository extends JpaRepository<DroneBatteryEvent, Long> {

    @Query("SELECT dbe FROM DroneBatteryEvent dbe " +
            "JOIN FETCH dbe.drone " +
            "WHERE dbe.drone.id = :droneId " +
            "ORDER BY dbe.createdAt DESC")
    List<DroneBatteryEvent> findAllByDroneId(int droneId);
}
