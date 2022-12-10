package alex.silantev.dronzilla.dtos;

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
@Schema(description = "Drone with cargo info")
public class DroneWithCargoDto extends DroneSummaryDto {

    @Schema(description = "Cargo")
    private List<OrderItemDto> cargo;
}
