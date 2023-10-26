package com.example.termines.controller;

import com.example.termines.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class TerminesController {
    private final FileService fileService;

    @PostMapping("/api/v1/download")
    public void read(@RequestParam("file") MultipartFile file, HttpServletResponse response) throws IOException {
        fileService.readFile(file, response);
    }
}
