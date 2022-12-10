package alex.silantev.dronzilla.repository;

import alex.silantev.dronzilla.models.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Integer> {
}
