package alex.silantev.dronzilla.controllers;

import alex.silantev.dronzilla.controllers.docs.MedicationControllerDocs;
import alex.silantev.dronzilla.dto.FileDataDto;
import alex.silantev.dronzilla.dto.MedicationCreateRequest;
import alex.silantev.dronzilla.dto.MedicationDto;
import alex.silantev.dronzilla.services.FileStorage;
import alex.silantev.dronzilla.services.MedicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
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
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/medications", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicationController implements MedicationControllerDocs {

    private final FileStorage fileStorage;
    private final MedicationService medicationService;

    @Override
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MedicationDto create(@RequestParam @NotBlank @Pattern(regexp = "^[\\d\\w\\-_]+$") String name,
                                @RequestParam @Positive Integer weight,
                                @RequestParam @NotBlank @Pattern(regexp = "^[\\dA-Z_]+$") String code,
                                @RequestPart(required = false) MultipartFile image) throws IOException {

        String imagePath = null;
        if (image != null && !image.isEmpty()) {
            imagePath = fileStorage.add(FileDataDto.builder()
                    .data(image.getBytes())
                    .filename(image.getOriginalFilename()).build());
        }
        MedicationCreateRequest medicationCreateRequest = MedicationCreateRequest.builder()
                .name(name)
                .weight(weight)
                .code(code)
                .image(imagePath)
                .build();
        return medicationService.create(medicationCreateRequest);
    }

    @Override
    @GetMapping
    public List<MedicationDto> findAll() {
        return medicationService.findAll();
    }
}
