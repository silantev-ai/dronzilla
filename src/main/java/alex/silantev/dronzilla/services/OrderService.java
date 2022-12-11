package alex.silantev.dronzilla.services;

import alex.silantev.dronzilla.dtos.DroneLoadRequest;

public interface OrderService {

    void create(int droneId, DroneLoadRequest droneLoadRequest);

    void finish(int droneId);
}
