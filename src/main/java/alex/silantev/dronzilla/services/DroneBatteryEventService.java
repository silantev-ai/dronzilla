package alex.silantev.dronzilla.services;

import alex.silantev.dronzilla.dto.DroneBatteryAuditDto;

public interface DroneBatteryEventService {

    DroneBatteryAuditDto findAllByDroneId(int droneId);
}
