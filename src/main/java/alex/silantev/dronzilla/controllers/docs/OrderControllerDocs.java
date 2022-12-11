package alex.silantev.dronzilla.controllers.docs;

import alex.silantev.dronzilla.dto.DroneDeliveryInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;


@Tag(name = "Order")
public interface OrderControllerDocs extends BaseControllerDocs {

    @Operation(summary = "Finishing delivery")
    void finishDelivery(int droneId);

    @Operation(summary = "Drone delivery information",
            responses = @ApiResponse(responseCode = "200", content =
                    {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = DroneDeliveryInfoDto.class))}))
    DroneDeliveryInfoDto getDroneDeliveryInfo(int droneId);
}
