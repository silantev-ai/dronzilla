package alex.silantev.dronzilla.services.Impl;

import alex.silantev.dronzilla.constraints.BizConstraintProcessor;
import alex.silantev.dronzilla.constraints.DroneRegisterBizConstraint;
import alex.silantev.dronzilla.constraints.LoadDroneBizConstraint;
import alex.silantev.dronzilla.dtos.DroneCreateRequest;
import alex.silantev.dronzilla.dtos.DroneWithCargoDto;
import alex.silantev.dronzilla.dtos.DroneLoadRequest;
import alex.silantev.dronzilla.dtos.DroneSummaryDto;
import alex.silantev.dronzilla.dtos.MedicationDto;
import alex.silantev.dronzilla.dtos.OrderItemDto;
import alex.silantev.dronzilla.enums.DroneState;
import alex.silantev.dronzilla.mappers.DroneMapper;
import alex.silantev.dronzilla.mappers.Mappers;
import alex.silantev.dronzilla.mappers.MedicationMapper;
import alex.silantev.dronzilla.models.Drone;
import alex.silantev.dronzilla.models.Order;
import alex.silantev.dronzilla.models.OrderItem;
import alex.silantev.dronzilla.repository.DroneRepository;
import alex.silantev.dronzilla.repository.MedicationRepository;
import alex.silantev.dronzilla.repository.OrderRepository;
import alex.silantev.dronzilla.services.DroneService;
import alex.silantev.dronzilla.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;
    private final OrderRepository orderRepository;
    private final MedicationRepository medicationRepository;
    private final OrderService orderService;
    private final DroneMapper droneMapper = Mappers.droneMapper;
    private final MedicationMapper medicationMapper = Mappers.medicationMapper;

    @Override
    @Transactional
    public DroneSummaryDto register(DroneCreateRequest droneCreateRequest) {
        BizConstraintProcessor.of(
                DroneRegisterBizConstraint.builder()
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
    public DroneSummaryDto loadDrone(int id, DroneLoadRequest droneLoadRequest) {
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
    public DroneWithCargoDto getById(int id) {
        Drone drone = droneRepository.getEntityOrThrow(id);
        List<OrderItemDto> cargo = orderRepository.findActiveOrder(drone.getId())
                .map(Order::getOrderItems)
                .stream()
                .flatMap(Collection::stream)
                .map(orderItem ->
                    new OrderItemDto(orderItem.getAmount(), medicationMapper.mapMedication(orderItem.getMedication()))
                )
                .collect(Collectors.toList());
        return droneMapper.mapDrone(drone, cargo);
    }

    @Override
    public List<DroneSummaryDto> findAll() {
        return droneRepository.findAll()
                .stream()
                .map(droneMapper::mapDrone)
                .collect(Collectors.toList());
    }
}
