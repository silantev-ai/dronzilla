package alex.silantev.dronzilla.mappers;

import alex.silantev.dronzilla.dto.DroneBatteryAuditDto;
import alex.silantev.dronzilla.models.Drone;
import alex.silantev.dronzilla.models.DroneBatteryEvent;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface DroneBatteryEventMapper {

    DroneBatteryAuditDto mapDroneAudit(Drone drone, List<DroneBatteryAuditDto.DroneBatteryEventDto> droneBatteryEvents);

    DroneBatteryAuditDto.DroneBatteryEventDto mapDroneBatteryEvent(DroneBatteryEvent droneBatteryEvent);
}
