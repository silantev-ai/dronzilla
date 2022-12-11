package alex.silantev.dronzilla.services;

import alex.silantev.dronzilla.dto.DroneDeliveryInfoDto;
import alex.silantev.dronzilla.dto.DroneLoadRequest;

public interface OrderService {

    void create(int droneId, DroneLoadRequest droneLoadRequest);

    DroneDeliveryInfoDto getDroneDeliveryInfo(int droneId);

    void finishDelivery(int droneId);
}
