package alex.silantev.dronzilla.constraints;

import alex.silantev.dronzilla.dtos.DroneLoadRequest;
import alex.silantev.dronzilla.enums.DroneState;
import alex.silantev.dronzilla.enums.ErrorCode;
import alex.silantev.dronzilla.exceptions.BizServiceException;
import alex.silantev.dronzilla.models.Drone;
import alex.silantev.dronzilla.models.Medication;
import alex.silantev.dronzilla.repository.DroneRepository;
import alex.silantev.dronzilla.repository.MedicationRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
public class LoadDroneBizConstraint implements BizConstraint {

    private static int MIN_BATTERY_CAPACITY_TO_GO = 25;

    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;
    private final int id;
    private final DroneLoadRequest droneLoadRequest;

    @Getter
    private Drone drone;
    @Getter
    private List<Medication> medications;

    @Override
    public void check() throws BizServiceException {
        drone = droneRepository.getEntityOrThrow(id);
        checkDroneState();
        checkDroneBatteryCapacity();
        checkMedicationAmounts();

        Map<Integer, Integer> medicationAmountMap = droneLoadRequest.getMedicationAmounts()
                .stream()
                .collect(Collectors.toMap(
                        DroneLoadRequest.MedicationAmount::getId,
                        DroneLoadRequest.MedicationAmount::getAmount));
        medications = medicationRepository.findAllById(medicationAmountMap.keySet());
        checkMedications(medicationAmountMap.keySet());
        checkOverWeight(medicationAmountMap);
    }

    private void checkDroneState() {
        if (drone.getDroneState() != DroneState.IDLE) {
            throw new BizServiceException(ErrorCode.DRONE_IS_BUSY);
        }
    }

    private void checkDroneBatteryCapacity() {
        if (drone.getBatteryCapacity() < MIN_BATTERY_CAPACITY_TO_GO) {
            throw new BizServiceException(ErrorCode.DRONE_LOW_BATTERY);
        }
    }

    private void checkMedicationAmounts() {
        int uniqueMedications = droneLoadRequest.getMedicationAmounts().stream()
                .map(DroneLoadRequest.MedicationAmount::getId)
                .collect(Collectors.toSet())
                .size();

        if (uniqueMedications != droneLoadRequest.getMedicationAmounts().size()) {
            throw new BizServiceException(ErrorCode.VALIDATION_ERROR);
        }
    }

    private void checkMedications(Set<Integer> medicationIds) {
        Set<Integer> existedIds = medications
                .stream()
                .map(Medication::getId)
                .collect(Collectors.toSet());

        Set<Integer> diffIds = medicationIds.stream()
                .filter(Predicate.not(existedIds::contains))
                .collect(Collectors.toSet());
        if (!diffIds.isEmpty()) {
            throw new BizServiceException(ErrorCode.VALIDATION_ERROR);
        }
    }

    private void checkOverWeight(Map<Integer, Integer> medicationAmountMap) {
        int totalWeight = medications.stream()
                .mapToInt(medication ->
                        medication.getWeight() * medicationAmountMap.getOrDefault(medication.getId(), 1))
                .sum();
        if (totalWeight > drone.getWeightLimit()) {
            throw new BizServiceException(ErrorCode.OVERWEIGHT_CARGO);
        }
    }
}
