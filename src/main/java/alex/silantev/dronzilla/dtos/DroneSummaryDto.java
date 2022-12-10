package alex.silantev.dronzilla.dtos;

import alex.silantev.dronzilla.enums.DroneModel;
import alex.silantev.dronzilla.enums.DroneState;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Drone summary info")
public class DroneSummaryDto {

    @Schema(description = "Id")
    private Integer id;

    @Schema(description = "Serial number")
    private String serialNumber;

    @Schema(description = "Drone model")
    private DroneModel droneModel;

    @Schema(description = "Weight limit (gram)")
    private Integer weightLimit;

    @Schema(description = "Battery capacity (percentage)")
    private Integer batteryCapacity;

    @Schema(description = "Drone state")
    private DroneState droneState;
}
