package alex.silantev.dronzilla.controllers.docs;

import alex.silantev.dronzilla.dto.DroneCreateRequest;
import alex.silantev.dronzilla.dto.DroneDto;
import alex.silantev.dronzilla.dto.DroneUpdateRequest;
import alex.silantev.dronzilla.dto.DroneLoadRequest;
import alex.silantev.dronzilla.enums.DroneState;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Drone")
public interface DroneControllerDocs extends BaseControllerDocs {

    @Operation(summary = "Drone registration",
            responses = @ApiResponse(responseCode = "200", content =
                    {@Content(mediaType = "application/json", schema = @Schema(implementation = DroneDto.class))}))
    DroneDto register(DroneCreateRequest droneCreateRequest);

    @Operation(summary = "Drone update",
            responses = @ApiResponse(responseCode = "200", content =
                    {@Content(mediaType = "application/json", schema = @Schema(implementation = DroneDto.class))}))
    DroneDto update(int id, DroneUpdateRequest droneUpdateRequest);

    @Operation(summary = "Load drone with medications",
            responses = @ApiResponse(responseCode = "200", content =
                    {@Content(mediaType = "application/json", schema = @Schema(implementation = DroneDto.class))}))
    DroneDto loadDrone(int id, DroneLoadRequest droneLoadRequest);

    @Operation(summary = "Drone information",
            responses = @ApiResponse(responseCode = "200", content =
                    {@Content(mediaType = "application/json", schema = @Schema(implementation = DroneDto.class))}))
    DroneDto getById(int id);

    @Operation(summary = "drones")
    List<DroneDto> findAll(DroneState droneState);
}
