package alex.silantev.dronzilla.repository;

import alex.silantev.dronzilla.enums.OrderStatus;
import alex.silantev.dronzilla.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT o FROM Order o " +
            "JOIN FETCH o.orderItems " +
            "WHERE o.drone.id = :droneId AND o.status = :status")
    List<Order> findAllByDroneIdAndStatus(int droneId, OrderStatus status);

    default Optional<Order> findActiveOrder(int droneId) {
        return findAllByDroneIdAndStatus(droneId, OrderStatus.ACTIVE).stream().findFirst();
    }
}
