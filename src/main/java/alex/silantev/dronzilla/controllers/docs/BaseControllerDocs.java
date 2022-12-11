package alex.silantev.dronzilla.controllers.docs;

import alex.silantev.dronzilla.dto.ErrorResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;

@ApiResponse(responseCode = "400", description = "Bad request",
        content =  @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class)))
public interface BaseControllerDocs {
}
