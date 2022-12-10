package alex.silantev.dronzilla.controllers.docs;

import alex.silantev.dronzilla.dtos.ErrorResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@ApiResponse(responseCode = "400", description = "Bad request",
        content =  @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
public interface BaseControllerDocs {
}