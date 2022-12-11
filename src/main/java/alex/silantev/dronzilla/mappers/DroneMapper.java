package alex.silantev.dronzilla.mappers;

import alex.silantev.dronzilla.dto.DroneDeliveryInfoDto;
import alex.silantev.dronzilla.dto.DroneDto;
import alex.silantev.dronzilla.dto.OrderItemDto;
import alex.silantev.dronzilla.models.Drone;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface DroneMapper {

    DroneDto mapDrone(Drone drone);

    DroneDeliveryInfoDto mapDrone(Drone drone, List<OrderItemDto> cargo);
}
