package alex.silantev.dronzilla.mappers;

import alex.silantev.dronzilla.dto.MedicationDto;
import alex.silantev.dronzilla.models.Medication;
import org.mapstruct.Mapper;

@Mapper
public interface MedicationMapper {

    MedicationDto mapMedication(Medication medication);
}
