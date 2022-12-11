package alex.silantev.dronzilla.dto;

import alex.silantev.dronzilla.enums.ErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Schema(description = "Error")
@AllArgsConstructor
public class ErrorResponse {

    @Schema(description = "Error code")
    private ErrorCode code;

    @Schema(description = "Error details")
    private String details;

    public ErrorResponse(ErrorCode code) {
        this.code = code;
    }
}
