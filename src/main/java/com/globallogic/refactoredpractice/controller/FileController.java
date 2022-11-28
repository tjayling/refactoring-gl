package com.globallogic.refactoredpractice.controller;

import com.globallogic.refactoredpractice.mapper.FileMapper;
import com.globallogic.refactoredpractice.model.File;
import com.globallogic.refactoredpractice.payload.FileResponse;
import com.globallogic.refactoredpractice.service.FileService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/files")
@CrossOrigin(origins = "*")
public class FileController {

    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public FileResponse uploadFile(@RequestParam("file") MultipartFile incomingFile) {
        File file = fileService.saveFile(incomingFile);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/download/").path(file.getFileName()).toUriString();
        return new FileResponse(file.getFileName(), fileDownloadUri, incomingFile.getContentType(), incomingFile.getSize());
    }

    @PostMapping("/upload-many")
    public List<FileResponse> uploadFiles(@RequestParam("files") MultipartFile[] incomingFiles) {
        return Arrays.asList(incomingFiles).stream().map(file -> uploadFile(file)).collect(Collectors.toList());

    }




    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFileByName(@PathVariable String fileName, HttpServletRequest request) {
        File file = fileService.findByName(fileName);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .body(new ByteArrayResource(file.getData()));
    }

    @GetMapping("/download-file-by-id/{fileId:.+}")
    public ResponseEntity<Resource> downloadFileById(@PathVariable String fileId, HttpServletRequest request) {
        // Load file as Resource
        File file = fileService.findById(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .body(new ByteArrayResource(file.getData()));
    }
}
