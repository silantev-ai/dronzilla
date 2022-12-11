package alex.silantev.dronzilla.constraints;

import alex.silantev.dronzilla.dto.DroneUpdateRequest;
import alex.silantev.dronzilla.enums.DroneState;
import alex.silantev.dronzilla.enums.ErrorCode;
import alex.silantev.dronzilla.exceptions.BizServiceException;
import alex.silantev.dronzilla.models.Drone;
import alex.silantev.dronzilla.repository.DroneRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Builder
@AllArgsConstructor
public class DroneUpdateBizConstraint implements BizConstraint {

    private static Map<DroneState, DroneState> DRONE_STATE_TRANSITIONS = new HashMap<>();

    static {
        DRONE_STATE_TRANSITIONS.put(DroneState.IDLE, null);
        DRONE_STATE_TRANSITIONS.put(DroneState.LOADING, null);
        DRONE_STATE_TRANSITIONS.put(DroneState.LOADED, DroneState.DELIVERING);
        DRONE_STATE_TRANSITIONS.put(DroneState.DELIVERING, null);
        DRONE_STATE_TRANSITIONS.put(DroneState.DELIVERED, DroneState.RETURNING);
        DRONE_STATE_TRANSITIONS.put(DroneState.RETURNING, DroneState.IDLE);
    }

    private final int id;
    private final DroneUpdateRequest droneUpdateRequest;
    private final DroneRepository droneRepository;

    @Getter
    private Drone drone;

    @Override
    public void check() throws BizServiceException {
        drone = droneRepository.getEntityOrThrow(id);

        if (droneUpdateRequest.getDroneState() != null) {
            checkDroneStateTransitions();
        }
    }

    private void checkDroneStateTransitions() {
        if (DRONE_STATE_TRANSITIONS.get(drone.getDroneState()) != droneUpdateRequest.getDroneState()) {
            throw new BizServiceException(ErrorCode.DRONE_INVALID_STATE);
        }
    }

}
