package alex.silantev.dronzilla.constraints;

import alex.silantev.dronzilla.dto.DroneCreateRequest;
import alex.silantev.dronzilla.exceptions.BizServiceException;
import alex.silantev.dronzilla.enums.ErrorCode;
import alex.silantev.dronzilla.repository.DroneRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
public class RegistrationDroneBizConstraint implements BizConstraint {

    private final DroneRepository droneRepository;
    private final DroneCreateRequest droneCreateRequest;

    @Override
    public void check() throws BizServiceException {
        if (droneRepository.findBySerialNumber(droneCreateRequest.getSerialNumber()).isPresent()) {
            throw new BizServiceException(ErrorCode.DRONE_SERIAL_NUMBER_NOT_UNIQUE);
        }
    }
}
