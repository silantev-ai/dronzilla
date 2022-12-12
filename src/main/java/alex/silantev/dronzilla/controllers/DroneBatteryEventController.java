package alex.silantev.dronzilla.controllers;

import alex.silantev.dronzilla.controllers.docs.DroneBatterEventControllerDocs;
import alex.silantev.dronzilla.controllers.docs.MedicationControllerDocs;
import alex.silantev.dronzilla.dto.DroneBatteryAuditDto;
import alex.silantev.dronzilla.dto.FileDataDto;
import alex.silantev.dronzilla.dto.MedicationCreateRequest;
import alex.silantev.dronzilla.dto.MedicationDto;
import alex.silantev.dronzilla.services.DroneBatteryEventService;
import alex.silantev.dronzilla.services.FileStorage;
import alex.silantev.dronzilla.services.MedicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping(value = "/api/v1/drones/{id}/battery-audit", produces = MediaType.APPLICATION_JSON_VALUE)
public class DroneBatteryEventController implements DroneBatterEventControllerDocs {

    private final DroneBatteryEventService droneBatteryEventService;

    @Override
    @GetMapping
    public DroneBatteryAuditDto findAll(@PathVariable("id") int droneId) {
        return droneBatteryEventService.findAllByDroneId(droneId);
    }
}
