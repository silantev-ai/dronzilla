package alex.silantev.dronzilla.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Drone load info")
public class DroneLoadRequest {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "Medication quantity info")
    public static class MedicationAmount {

        @NotNull
        @Schema(description = "Id")
        private Integer id;

        @NotNull
        @Positive
        @Schema(description = "Quantity")
        private Integer amount;
    }

    @NotEmpty
    @Schema(description = "Medications")
    private List<MedicationAmount> medicationAmounts;
}
