package alex.silantev.dronzilla.controllers.docs;

import alex.silantev.dronzilla.dtos.DroneCreateRequest;
import alex.silantev.dronzilla.dtos.DroneUpdateRequest;
import alex.silantev.dronzilla.dtos.DroneWithCargoDto;
import alex.silantev.dronzilla.dtos.DroneLoadRequest;
import alex.silantev.dronzilla.dtos.DroneSummaryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Drone")
public interface DroneControllerDocs extends BaseControllerDocs {

    @Operation(summary = "Drone registration",
            responses = @ApiResponse(responseCode = "200", content =
                    {@Content(mediaType = "application/json", schema = @Schema(implementation = DroneSummaryDto.class))}))
    DroneSummaryDto register(DroneCreateRequest droneCreateRequest);

    @Operation(summary = "Drone update",
            responses = @ApiResponse(responseCode = "200", content =
                    {@Content(mediaType = "application/json", schema = @Schema(implementation = DroneSummaryDto.class))}))
    DroneSummaryDto update(int id, DroneUpdateRequest droneUpdateRequest);

    @Operation(summary = "Load drone with medications",
            responses = @ApiResponse(responseCode = "200", content =
                    {@Content(mediaType = "application/json", schema = @Schema(implementation = DroneSummaryDto.class))}))
    DroneSummaryDto loadDrone(int id, DroneLoadRequest droneLoadRequest);

    @Operation(summary = "Drone detailed information",
            responses = @ApiResponse(responseCode = "200", content =
                    {@Content(mediaType = "application/json", schema = @Schema(implementation = DroneWithCargoDto.class))}))
    DroneWithCargoDto getById(int id);

    @Operation(summary = "drones")
    List<DroneSummaryDto> findAll();
}
