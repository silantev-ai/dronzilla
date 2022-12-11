package alex.silantev.dronzilla.constraints;

import alex.silantev.dronzilla.enums.DroneState;
import alex.silantev.dronzilla.enums.ErrorCode;
import alex.silantev.dronzilla.exceptions.BizServiceException;
import alex.silantev.dronzilla.models.Drone;
import alex.silantev.dronzilla.models.Order;
import alex.silantev.dronzilla.repository.DroneRepository;
import alex.silantev.dronzilla.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Optional;


@Builder
@AllArgsConstructor
public class DroneFinishDeliveryBizConstraint implements BizConstraint {

    private final int id;
    private final OrderRepository orderRepository;

    @Getter
    private Order order;

    @Override
    public void check() throws BizServiceException {
        Optional<Order> activeOrder = orderRepository.findActiveOrder(id);
        if (activeOrder.isEmpty()) {
            throw new BizServiceException(ErrorCode.VALIDATION_ERROR);
        }

        order = activeOrder.get();

        if (order.getDrone().getDroneState() != DroneState.DELIVERING) {
            throw new BizServiceException(ErrorCode.DRONE_INVALID_STATE);
        }
    }
}
