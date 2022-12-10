package alex.silantev.dronzilla.models;

import alex.silantev.dronzilla.enums.DroneModel;
import alex.silantev.dronzilla.enums.DroneState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "drones")
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "serial_number", nullable = false, unique = true, length = 100)
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "drone_model", nullable = false, length = 50)
    private DroneModel droneModel;

    @Column(name = "weight_limit", nullable = false)
    private Integer weightLimit;

    @Column(name = "battery_capacity", nullable = false)
    private Integer batteryCapacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "drone_state", nullable = false, length = 50)
    private DroneState droneState;

}
