package alex.silantev.dronzilla.services.Impl;

import alex.silantev.dronzilla.dto.DroneBatteryAuditDto;
import alex.silantev.dronzilla.enums.ErrorCode;
import alex.silantev.dronzilla.exceptions.BizServiceException;
import alex.silantev.dronzilla.mappers.DroneBatteryEventMapper;
import alex.silantev.dronzilla.mappers.Mappers;
import alex.silantev.dronzilla.models.Drone;
import alex.silantev.dronzilla.models.DroneBatteryEvent;
import alex.silantev.dronzilla.repository.DroneBatteryEventRepository;
import alex.silantev.dronzilla.repository.DroneRepository;
import alex.silantev.dronzilla.services.DroneBatteryEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DroneBatteryEventServiceImpl implements DroneBatteryEventService {

    private final DroneRepository droneRepository;
    private final DroneBatteryEventRepository droneBatteryEventRepository;
    private final DroneBatteryEventMapper droneBatteryEventMapper = Mappers.droneBatteryEventMapper;

    @Transactional
    @Scheduled(fixedDelayString = "#{(${application.drone-battery-check.interval:1} * 1000 * 60)}")
    public void checkDroneBattery() {
        List<DroneBatteryEvent> droneBatteryEvents = droneRepository.findAll()
                .stream()
                .map(drone -> DroneBatteryEvent.builder()
                        .drone(drone)
                        .batteryLevel(drone.getBatteryCapacity())
                        .build())
                .collect(Collectors.toList());
        droneBatteryEventRepository.saveAll(droneBatteryEvents);
    }

    @Override
    @Transactional(readOnly = true)
    public DroneBatteryAuditDto findAllByDroneId(int droneId) {
        List<DroneBatteryEvent> droneBatteryEvents = droneBatteryEventRepository.findAllByDroneId(droneId);
        Drone drone = droneBatteryEvents.stream()
                .map(DroneBatteryEvent::getDrone)
                .findFirst()
                .orElseThrow(() -> new BizServiceException(ErrorCode.DRONE_NOT_FOUND));

        List<DroneBatteryAuditDto.DroneBatteryEventDto> droneBatteryEventsDto = droneBatteryEvents.stream()
                .map(droneBatteryEventMapper::mapDroneBatteryEvent)
                .collect(Collectors.toList());
        return droneBatteryEventMapper.mapDroneAudit(drone, droneBatteryEventsDto);
    }
}
