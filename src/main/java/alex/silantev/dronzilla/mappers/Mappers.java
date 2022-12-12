package alex.silantev.dronzilla.mappers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Mappers {

    public static DroneMapper droneMapper = org.mapstruct.factory.Mappers.getMapper(DroneMapper.class);
    public static MedicationMapper medicationMapper = org.mapstruct.factory.Mappers.getMapper(MedicationMapper.class);
    public static DroneBatteryEventMapper droneBatteryEventMapper = org.mapstruct.factory.Mappers.getMapper(DroneBatteryEventMapper.class);
}
