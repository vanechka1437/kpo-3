package com.antiplagiat.storage.service;

import com.antiplagiat.storage.entity.FileEntity;
import com.antiplagiat.storage.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileStorageService {
    private final FileRepository fileRepository;

    @Autowired
    public FileStorageService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public String storeFile(MultipartFile file) throws IOException {
        String fileId = UUID.randomUUID().toString();
        FileEntity entity = new FileEntity();
        entity.setId(UUID.fromString(fileId));
        entity.setName(file.getOriginalFilename());
        entity.setContent(file.getBytes());
        fileRepository.save(entity);
        return fileId;
    }

    public FileEntity getFile(String id) {
        return fileRepository.findById(UUID.fromString(id)).orElse(null);
    }
}