package alex.silantev.dronzilla.dto;

import alex.silantev.dronzilla.enums.DroneState;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DroneUpdateRequest")
public class DroneUpdateRequest {

    @Max(100)
    @PositiveOrZero
    @Schema(description = "Battery capacity (percentage)")
    private Integer batteryCapacity;

    @Schema(description = "Drone state")
    private DroneState droneState;
}
