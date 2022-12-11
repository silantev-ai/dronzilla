package alex.silantev.dronzilla.controllers;

import alex.silantev.dronzilla.controllers.docs.DroneControllerDocs;
import alex.silantev.dronzilla.dto.DroneCreateRequest;
import alex.silantev.dronzilla.dto.DroneDto;
import alex.silantev.dronzilla.dto.DroneUpdateRequest;
import alex.silantev.dronzilla.dto.DroneLoadRequest;
import alex.silantev.dronzilla.services.DroneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/drones", produces = "application/json")
public class DroneController implements DroneControllerDocs {

    private final DroneService droneService;

    @Override
    @PostMapping
    public DroneDto register(@RequestBody @Valid DroneCreateRequest droneCreateRequest) {
        return droneService.register(droneCreateRequest);
    }

    @Override
    @PutMapping("/{id}")
    public DroneDto update(@PathVariable("id") int id, @RequestBody @Valid DroneUpdateRequest droneUpdateRequest) {
        return droneService.update(id, droneUpdateRequest);
    }

    @Override
    @PostMapping("/{id}/load")
    public DroneDto loadDrone(@PathVariable("id") int id, @RequestBody @Valid DroneLoadRequest droneLoadRequest) {
        return droneService.loadDrone(id, droneLoadRequest);
    }

    @Override
    @GetMapping("/{id}")
    public DroneDto getById(@PathVariable("id") int id) {
        return droneService.getById(id);
    }

    @Override
    @GetMapping
    public List<DroneDto> findAll() {
        return droneService.findAll();
    }
}
