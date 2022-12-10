package alex.silantev.dronzilla.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Medication info")
public class MedicationDto {

    @Schema(description = "Id")
    private Integer id;

    @Schema(description = "Name")
    private String name;

    @Schema(description = "Weight")
    private Integer weight;

    @Schema(description = "Code")
    private String code;

    @Schema(description = "Image path")
    private String image;
}
