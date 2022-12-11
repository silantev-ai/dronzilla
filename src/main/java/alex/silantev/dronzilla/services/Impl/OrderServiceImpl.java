package alex.silantev.dronzilla.services.Impl;

import alex.silantev.dronzilla.constraints.BizConstraintProcessor;
import alex.silantev.dronzilla.constraints.DroneFinishDeliveryBizConstraint;
import alex.silantev.dronzilla.dtos.DroneLoadRequest;
import alex.silantev.dronzilla.enums.DroneState;
import alex.silantev.dronzilla.enums.OrderStatus;
import alex.silantev.dronzilla.models.Drone;
import alex.silantev.dronzilla.models.Medication;
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

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;

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
    @Transactional
    public void finish(int droneId) {
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
