package alex.silantev.dronzilla.services;

import alex.silantev.dronzilla.dto.MedicationCreateRequest;
import alex.silantev.dronzilla.dto.MedicationDto;

import java.util.List;

public interface MedicationService {

    MedicationDto create(MedicationCreateRequest medicationCreateRequest);

    List<MedicationDto> findAll();
}
