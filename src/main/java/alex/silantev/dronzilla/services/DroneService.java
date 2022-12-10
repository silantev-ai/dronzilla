package alex.silantev.dronzilla.services;

import alex.silantev.dronzilla.dtos.DroneCreateRequest;
import alex.silantev.dronzilla.dtos.DroneWithCargoDto;
import alex.silantev.dronzilla.dtos.DroneLoadRequest;
import alex.silantev.dronzilla.dtos.DroneSummaryDto;

import java.util.List;

public interface DroneService {

    DroneSummaryDto register(DroneCreateRequest droneCreateRequest);

    DroneSummaryDto loadDrone(int id, DroneLoadRequest droneLoadRequest);

    DroneWithCargoDto getById(int id);

    List<DroneSummaryDto> findAll();
}
