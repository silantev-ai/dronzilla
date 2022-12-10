package alex.silantev.dronzilla.mappers;

import alex.silantev.dronzilla.dtos.DroneWithCargoDto;
import alex.silantev.dronzilla.dtos.DroneSummaryDto;
import alex.silantev.dronzilla.dtos.MedicationDto;
import alex.silantev.dronzilla.dtos.OrderItemDto;
import alex.silantev.dronzilla.models.Drone;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface DroneMapper {

    DroneSummaryDto mapDrone(Drone drone);

    DroneWithCargoDto mapDrone(Drone drone, List<OrderItemDto> cargo);
}
