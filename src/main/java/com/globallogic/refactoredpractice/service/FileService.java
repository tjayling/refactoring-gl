package com.globallogic.refactoredpractice.service;

import com.globallogic.refactoredpractice.exception.FileStorageException;
import com.globallogic.refactoredpractice.mapper.FileMapper;
import com.globallogic.refactoredpractice.model.File;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileService {

    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public File saveFile(MultipartFile incomingFile) {
        String fileName = StringUtils.cleanPath(incomingFile.getOriginalFilename());
        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("Filename contains invalid path sequence '..' " + fileName);
            }

            return fileMapper.saveFile(fileName, incomingFile.getContentType(), incomingFile.getBytes() );
        } catch (IOException ioe) {
            try {
                throw new FileStorageException(fileName + " could not be saved");
            } catch (FileStorageException fse) {
                throw new RuntimeException(fse);
            }
        }
    }

    public File findByName(String fileName) {
//        try {
        return fileMapper.findByName(fileName); //.orElseThrow(() -> new FileNotFoundException(fileName + " could not be found"));
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }

    public File findById(String fileId) {
//        try {
        return fileMapper.findById(fileId); //.orElseThrow(() -> new FileNotFoundException(fileId + " could not be found"));
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }
}
