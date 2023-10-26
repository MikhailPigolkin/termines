package com.example.termines.service;

import com.example.termines.entity.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface FileService {
    public void readFile(MultipartFile file, HttpServletResponse response) throws IOException;

    void saveToFile(FileEntity fileEntity, HttpServletResponse response) throws IOException;
}
