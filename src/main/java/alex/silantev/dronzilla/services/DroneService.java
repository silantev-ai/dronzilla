package alex.silantev.dronzilla.services;

import alex.silantev.dronzilla.dto.DroneCreateRequest;
import alex.silantev.dronzilla.dto.DroneDto;
import alex.silantev.dronzilla.dto.DroneUpdateRequest;
import alex.silantev.dronzilla.dto.DroneLoadRequest;
import alex.silantev.dronzilla.enums.DroneState;

import java.util.List;

public interface DroneService {

    DroneDto register(DroneCreateRequest droneCreateRequest);

    DroneDto update(int id, DroneUpdateRequest droneUpdateRequest);

    DroneDto loadDrone(int id, DroneLoadRequest droneLoadRequest);

    DroneDto getById(int id);

    List<DroneDto> findAll(DroneState droneState);
}
