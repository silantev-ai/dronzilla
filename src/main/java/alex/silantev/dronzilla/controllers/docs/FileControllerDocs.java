package alex.silantev.dronzilla.controllers.docs;

import alex.silantev.dronzilla.dto.MedicationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Tag(name = "File storage")
public interface FileControllerDocs extends BaseControllerDocs {

    @Operation(summary = "Get file")
    ResponseEntity<byte[]> get(String filePath);
}
