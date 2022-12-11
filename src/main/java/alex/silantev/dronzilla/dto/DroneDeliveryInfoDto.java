package alex.silantev.dronzilla.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Drone info with cargo")
public class DroneDeliveryInfoDto extends DroneDto {

    @Schema(description = "Cargo")
    private List<OrderItemDto> cargo;
}
