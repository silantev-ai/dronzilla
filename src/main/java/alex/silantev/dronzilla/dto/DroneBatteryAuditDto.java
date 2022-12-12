package alex.silantev.dronzilla.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Schema(description = "Drone battery audit")
public class DroneBatteryAuditDto extends DroneDto {

    @Getter
    @Setter
    @Schema(description = "Drone battery event info")
    public static class DroneBatteryEventDto {

        @Schema(description = "Id")
        private Long id;

        @Schema(description = "Battery level")
        private Integer batteryLevel;

        @Schema(description = "Created at")
        private LocalDateTime createdAt;
    }

    @Schema(description = "Drone battery events")
    private List<DroneBatteryEventDto> droneBatteryEvents;
}
