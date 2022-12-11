package alex.silantev.dronzilla.services.Impl;

import alex.silantev.dronzilla.constraints.BizConstraintProcessor;
import alex.silantev.dronzilla.constraints.DroneFinishDeliveryBizConstraint;
import alex.silantev.dronzilla.dto.DroneDeliveryInfoDto;
import alex.silantev.dronzilla.dto.DroneLoadRequest;
import alex.silantev.dronzilla.dto.OrderItemDto;
import alex.silantev.dronzilla.enums.DroneState;
import alex.silantev.dronzilla.enums.ErrorCode;
import alex.silantev.dronzilla.enums.OrderStatus;
import alex.silantev.dronzilla.exceptions.BizServiceException;
import alex.silantev.dronzilla.mappers.DroneMapper;
import alex.silantev.dronzilla.mappers.Mappers;
import alex.silantev.dronzilla.mappers.MedicationMapper;
import alex.silantev.dronzilla.models.Drone;
import alex.silantev.dronzilla.models.Order;
import alex.silantev.dronzilla.models.OrderItem;
import alex.silantev.dronzilla.repository.DroneRepository;
import alex.silantev.dronzilla.repository.MedicationRepository;
import alex.silantev.dronzilla.repository.OrderItemRepository;
import alex.silantev.dronzilla.repository.OrderRepository;
import alex.silantev.dronzilla.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;
    private final DroneMapper droneMapper = Mappers.droneMapper;
    private final MedicationMapper medicationMapper = Mappers.medicationMapper;

    @Async
    @Override
    @Transactional
    public void create(int droneId, DroneLoadRequest droneLoadRequest) {
        Drone drone = droneRepository.getEntityOrThrow(droneId);
        Order order = new Order();
        order.setStatus(OrderStatus.ACTIVE);
        order.setDrone(drone);
        orderRepository.save(order);
        for (DroneLoadRequest.MedicationAmount medicationAmount : droneLoadRequest.getMedicationAmounts()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setMedication(medicationRepository.getReferenceById(medicationAmount.getId()));
            orderItem.setAmount(medicationAmount.getAmount());
            orderItemRepository.save(orderItem);
        }
        drone.setDroneState(DroneState.LOADED);
        droneRepository.save(drone);
    }

    @Override
    @Transactional(readOnly = true)
    public DroneDeliveryInfoDto getDroneDeliveryInfo(int droneId) {
        Order order = orderRepository.findActiveOrder(droneId)
                .orElseThrow(() -> new BizServiceException(ErrorCode.VALIDATION_ERROR));
        List<OrderItemDto> cargo = order.getOrderItems()
                .stream()
                .map(orderItem ->
                        new OrderItemDto(orderItem.getAmount(), medicationMapper.mapMedication(orderItem.getMedication()))
                )
                .collect(Collectors.toList());
        return droneMapper.mapDrone(order.getDrone(), cargo);
    }

    @Override
    @Transactional
    public void finishDelivery(int droneId) {
        DroneFinishDeliveryBizConstraint constraint = DroneFinishDeliveryBizConstraint.builder()
                .id(droneId)
                .orderRepository(orderRepository)
                .build();
        BizConstraintProcessor.of(constraint).check();

        Order order = constraint.getOrder();
        order.setStatus(OrderStatus.ARCHIVED);
        orderRepository.save(order);

        Drone drone = order.getDrone();
        drone.setDroneState(DroneState.DELIVERED);
        droneRepository.save(drone);
    }
}
