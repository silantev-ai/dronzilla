package alex.silantev.dronzilla.services.Impl;

import alex.silantev.dronzilla.dto.MedicationCreateRequest;
import alex.silantev.dronzilla.dto.MedicationDto;
import alex.silantev.dronzilla.mappers.Mappers;
import alex.silantev.dronzilla.mappers.MedicationMapper;
import alex.silantev.dronzilla.models.Medication;
import alex.silantev.dronzilla.repository.MedicationRepository;
import alex.silantev.dronzilla.services.MedicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicationServiceImpl implements MedicationService {

    private final MedicationRepository medicationRepository;
    private final MedicationMapper medicationMapper = Mappers.medicationMapper;

    @Override
    @Transactional
    public MedicationDto create(MedicationCreateRequest medicationCreateRequest) {
        Medication medication = Medication.builder()
                .name(medicationCreateRequest.getName())
                .weight(medicationCreateRequest.getWeight())
                .code(medicationCreateRequest.getCode())
                .image(medicationCreateRequest.getImage())
                .build();
        medicationRepository.save(medication);
        return medicationMapper.mapMedication(medication);
    }

    @Override
    public List<MedicationDto> findAll() {
        return medicationRepository.findAll()
                .stream()
                .map(medicationMapper::mapMedication)
                .collect(Collectors.toList());
    }
}
