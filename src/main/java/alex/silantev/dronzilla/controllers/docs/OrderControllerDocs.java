package alex.silantev.dronzilla.controllers.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Order")
public interface OrderControllerDocs extends BaseControllerDocs {

    @Operation(summary = "Finishing delivery")
    void finishDelivery(int droneId);
}
