package alex.silantev.dronzilla.mappers;

import alex.silantev.dronzilla.dtos.MedicationDto;
import alex.silantev.dronzilla.models.Medication;
import org.mapstruct.Mapper;

@Mapper
public interface MedicationMapper {

    MedicationDto mapMedication(Medication medication);
}
