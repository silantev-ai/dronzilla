package alex.silantev.dronzilla.dtos;

import alex.silantev.dronzilla.enums.DroneModel;
import alex.silantev.dronzilla.enums.DroneState;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DroneCreateRequest")
public class DroneCreateRequest {

    @NotBlank
    @Size(max = 100)
    @Schema(description = "Serial number")
    private String serialNumber;

    @NotNull
    @Schema(description = "Drone model")
    private DroneModel droneModel;

    @NotNull
    @Positive
    @Max(500)
    @Schema(description = "Weight limit (gram)")
    private Integer weightLimit;

    @NotNull
    @Max(100)
    @PositiveOrZero
    @Schema(description = "Battery capacity (percentage)")
    private Integer batteryCapacity;
}
