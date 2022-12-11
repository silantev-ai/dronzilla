package alex.silantev.dronzilla.controllers;

import alex.silantev.dronzilla.controllers.docs.OrderControllerDocs;
import alex.silantev.dronzilla.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/orders", produces = "application/json")
public class OrderController implements OrderControllerDocs {

    private final OrderService orderService;

    @Override
    @PutMapping("/drone/{id}/finish-delivery")
    public void finishDelivery(@PathVariable("id") int droneId) {
        orderService.finish(droneId);
    }
}
