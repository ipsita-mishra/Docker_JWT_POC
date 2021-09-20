package com.learning.zoro.concepts.Controller;

import com.learning.zoro.concepts.Domain.Document;
import com.learning.zoro.concepts.Domain.FileInfo;
import com.learning.zoro.concepts.Domain.ResponseMessage;
import com.learning.zoro.concepts.Repository.FileUploadRepository;
import com.learning.zoro.concepts.Repository.UserRepository;
import com.learning.zoro.concepts.Service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin(origins = "*" , maxAge = 3600)
public class FileUploadController {
    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private FileUploadRepository fileUploadRepository;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            fileStorageService.save(file);
            message = file.getOriginalFilename() + " - Upload Successfully !!";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = file.getOriginalFilename() + " - Upload Failed !!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfos = fileStorageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FileUploadController.class, "getFile", path.getFileName().toString()).build().toString();

            return new FileInfo(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = fileStorageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
    @PostMapping("/upload/db")
    public ResponseEntity uploadToDB(@RequestParam("file") MultipartFile file) throws IOException {
        Document doc = new Document();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        doc.setDocName(fileName);
        try {
            doc.setFile(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileUploadRepository.save(doc);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/banking-service/")
                .path(fileName)
                .toUriString();
        return ResponseEntity.ok(fileDownloadUri);
    }
}
