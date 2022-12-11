package alex.silantev.dronzilla.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicationCreateRequest {

    private String name;
    private Integer weight;
    private String code;
    private String image;
}
