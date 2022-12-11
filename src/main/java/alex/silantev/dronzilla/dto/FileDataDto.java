package alex.silantev.dronzilla.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FileDataDto {

    private byte[] data;
    private String filename;
    private String contentType;
}
