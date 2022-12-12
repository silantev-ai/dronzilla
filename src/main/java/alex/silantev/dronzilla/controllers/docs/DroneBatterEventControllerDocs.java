package alex.silantev.dronzilla.controllers.docs;

import alex.silantev.dronzilla.dto.DroneBatteryAuditDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Drone battery audit")
public interface DroneBatterEventControllerDocs extends BaseControllerDocs {

    @Operation(summary = "Drone battery audit")
    DroneBatteryAuditDto findAll(int droneId);
}
