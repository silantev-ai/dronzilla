package alex.silantev.dronzilla.services.Impl;

import alex.silantev.dronzilla.dto.FileDataDto;
import alex.silantev.dronzilla.enums.ErrorCode;
import alex.silantev.dronzilla.exceptions.BizServiceException;
import alex.silantev.dronzilla.services.FileStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileStorageImpl implements FileStorage {

    @Value("${application.fileStorage.path}")
    private String basePath;

    @Override
    public FileDataDto get(String path) {
        File file = Path.of(basePath, path).toFile();
        if (!file.exists()) {
            throw new BizServiceException(ErrorCode.RESOURCE_NOT_FOUND);
        }
        try {
            String contentType = Files.probeContentType(file.toPath());
            try (InputStream is = new FileInputStream(file)) {
                byte[] bytes = new InputStreamResource(is).getInputStream().readAllBytes();
                return FileDataDto.builder()
                        .data(bytes)
                        .filename(file.getName())
                        .contentType(contentType)
                        .build();
            }
        } catch (IOException ex) {
            throw new BizServiceException(ErrorCode.IO_ERROR);
        }
    }

    @Override
    public String add(FileDataDto fileDataAddDto) {
        String fileDir = UUID.randomUUID().toString();
        Path filePath = createFileDir(fileDir).resolve(fileDataAddDto.getFilename());
        try {
            Files.copy(new ByteArrayInputStream(fileDataAddDto.getData()), filePath);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new BizServiceException(ErrorCode.IO_ERROR);
        }
        return Path.of(fileDir, fileDataAddDto.getFilename()).toString();
    }

    private Path createFileDir(String fileDir) {
        try {
            return Files.createDirectories(Path.of(basePath, fileDir));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new BizServiceException(ErrorCode.IO_ERROR);
        }
    }
}
