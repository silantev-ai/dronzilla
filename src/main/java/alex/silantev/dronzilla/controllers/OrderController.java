package alex.silantev.dronzilla.controllers;

import alex.silantev.dronzilla.controllers.docs.OrderControllerDocs;
import alex.silantev.dronzilla.dto.DroneDeliveryInfoDto;
import alex.silantev.dronzilla.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController implements OrderControllerDocs {

    private final OrderService orderService;

    @Override
    @PutMapping("/drone/{id}/finish-delivery")
    public void finishDelivery(@PathVariable("id") int droneId) {
        orderService.finishDelivery(droneId);
    }

    @Override
    @GetMapping("/drone/{id}/delivery-info")
    public DroneDeliveryInfoDto getDroneDeliveryInfo(@PathVariable("id") int droneId) {
        return orderService.getDroneDeliveryInfo(droneId);
    }
}
