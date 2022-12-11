package alex.silantev.dronzilla.controllers.docs;

import alex.silantev.dronzilla.dto.MedicationCreateRequest;
import alex.silantev.dronzilla.dto.MedicationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Tag(name = "Medication")
public interface MedicationControllerDocs extends BaseControllerDocs {

    @Operation(summary = "Medication creation",
            responses = @ApiResponse(responseCode = "200", content =
                    {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MedicationDto.class))}))
    MedicationDto create(String name, Integer weight, String code, MultipartFile image) throws IOException;

    @Operation(summary = "Medications")
    List<MedicationDto> findAll();
}
