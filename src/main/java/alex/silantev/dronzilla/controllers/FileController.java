package alex.silantev.dronzilla.controllers;

import alex.silantev.dronzilla.controllers.docs.FileControllerDocs;
import alex.silantev.dronzilla.controllers.docs.MedicationControllerDocs;
import alex.silantev.dronzilla.dto.FileDataDto;
import alex.silantev.dronzilla.dto.MedicationCreateRequest;
import alex.silantev.dronzilla.dto.MedicationDto;
import alex.silantev.dronzilla.services.FileStorage;
import alex.silantev.dronzilla.services.MedicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/files")
public class FileController implements FileControllerDocs {

    private final FileStorage fileStorage;

    @Override
    @GetMapping
    public ResponseEntity<byte[]> get(@RequestParam String filePath) {
        FileDataDto fileDataDto = fileStorage.get(filePath);
        ContentDisposition contentDispositionHeader = ContentDisposition.builder("inline")
                .filename(fileDataDto.getFilename())
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(contentDispositionHeader);
        try {
            MediaType mediaType = MediaType.parseMediaType(fileDataDto.getContentType());
            headers.setContentType(mediaType);
        } catch (Exception ignore) {}

        return ResponseEntity.ok()
                .headers(headers)
                .body(fileDataDto.getData());
    }
}
