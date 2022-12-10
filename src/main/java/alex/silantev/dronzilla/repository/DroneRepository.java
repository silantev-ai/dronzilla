package alex.silantev.dronzilla.repository;

import alex.silantev.dronzilla.models.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Integer> {
}
