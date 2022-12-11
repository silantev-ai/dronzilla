package alex.silantev.dronzilla.services;

import alex.silantev.dronzilla.dto.FileDataDto;

public interface FileStorage {

    FileDataDto get(String path);

    String add(FileDataDto fileDataAddDto);
}
