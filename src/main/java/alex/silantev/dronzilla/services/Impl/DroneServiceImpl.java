package alex.silantev.dronzilla.services.Impl;

import alex.silantev.dronzilla.constraints.BizConstraintProcessor;
import alex.silantev.dronzilla.constraints.DroneUpdateBizConstraint;
import alex.silantev.dronzilla.constraints.RegistrationDroneBizConstraint;
import alex.silantev.dronzilla.constraints.LoadDroneBizConstraint;
import alex.silantev.dronzilla.dto.DroneCreateRequest;
import alex.silantev.dronzilla.dto.DroneDto;
import alex.silantev.dronzilla.dto.DroneUpdateRequest;
import alex.silantev.dronzilla.dto.DroneLoadRequest;
import alex.silantev.dronzilla.enums.DroneState;
import alex.silantev.dronzilla.mappers.DroneMapper;
import alex.silantev.dronzilla.mappers.Mappers;
import alex.silantev.dronzilla.models.Drone;
import alex.silantev.dronzilla.repository.DroneRepository;
import alex.silantev.dronzilla.repository.MedicationRepository;
import alex.silantev.dronzilla.services.DroneService;
import alex.silantev.dronzilla.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;
    private final OrderService orderService;
    private final DroneMapper droneMapper = Mappers.droneMapper;

    @Override
    @Transactional
    public DroneDto register(DroneCreateRequest droneCreateRequest) {
        BizConstraintProcessor.of(
                RegistrationDroneBizConstraint.builder()
                        .droneRepository(droneRepository)
                        .droneCreateRequest(droneCreateRequest)
                        .build())
                .check();
        Drone drone = droneRepository.save(Drone.builder()
                .serialNumber(droneCreateRequest.getSerialNumber())
                .droneModel(droneCreateRequest.getDroneModel())
                .weightLimit(droneCreateRequest.getWeightLimit())
                .batteryCapacity(droneCreateRequest.getBatteryCapacity())
                .droneState(DroneState.IDLE)
                .build());
        return droneMapper.mapDrone(drone);
    }

    @Override
    @Transactional
    public DroneDto update(int id, DroneUpdateRequest droneUpdateRequest) {
        DroneUpdateBizConstraint constraint = DroneUpdateBizConstraint.builder()
                .id(id)
                .droneUpdateRequest(droneUpdateRequest)
                .droneRepository(droneRepository)
                .build();
        BizConstraintProcessor.of(constraint).check();
        Drone drone = constraint.getDrone();
        Optional.ofNullable(droneUpdateRequest.getBatteryCapacity()).ifPresent(drone::setBatteryCapacity);
        Optional.ofNullable(droneUpdateRequest.getDroneState()).ifPresent(drone::setDroneState);
        droneRepository.save(drone);
        return droneMapper.mapDrone(drone);
    }

    @Override
    @Transactional
    public DroneDto loadDrone(int id, DroneLoadRequest droneLoadRequest) {
        LoadDroneBizConstraint constraint = LoadDroneBizConstraint.builder()
                .droneRepository(droneRepository)
                .medicationRepository(medicationRepository)
                .id(id)
                .droneLoadRequest(droneLoadRequest)
                .build();
        BizConstraintProcessor.of(constraint).check();
        Drone drone = constraint.getDrone();
        drone.setDroneState(DroneState.LOADING);
        droneRepository.save(drone);
        orderService.create(drone.getId(), droneLoadRequest);
        return droneMapper.mapDrone(drone);
    }

    @Override
    @Transactional(readOnly = true)
    public DroneDto getById(int id) {
        Drone drone = droneRepository.getEntityOrThrow(id);
        return droneMapper.mapDrone(drone);
    }

    @Override
    public List<DroneDto> findAll(DroneState droneState) {
        return droneRepository.findAllByDroneState(droneState)
                .stream()
                .map(droneMapper::mapDrone)
                .collect(Collectors.toList());
    }
}
