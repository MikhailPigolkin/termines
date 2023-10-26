package com.example.termines.service;

import com.example.termines.entity.FileEntity;
import com.example.termines.entity.Term;
import com.example.termines.repository.FileEntityRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService{

    private final FileEntityRepository fileRepository;
    @Override
    public void readFile(MultipartFile file, HttpServletResponse response) throws IOException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));
        InputStream inputStream = file.getInputStream();
        List<Term> terms = new ArrayList<>();
        new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .forEach(line -> {
                    Term term = new Term();
                    Pattern pattern = Pattern.compile("-");
                    String[] words = pattern.split(line);
                    term.setTerm(words[0]);
                    term.setDescription(words[1]);
                    terms.add(term);
                });
        fileEntity.setTerms(terms);
        saveToFile(fileRepository.save(fileEntity), response);
    }

    @Override
    public void saveToFile(FileEntity fileEntity, HttpServletResponse response) throws IOException {
        String fileName = fileEntity.getFileName() + "_written.txt";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        File file = new File(fileName);
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(file));
        List<Term> terms = new ArrayList<>(fileEntity.getTerms());
        terms.sort((t1, t2) -> t1.getTerm().compareTo(t2.getTerm()));
        terms.forEach(t -> printWriter.println(t.getTerm() + "-" + t.getDescription()));
        printWriter.flush();
        InputStream is = new FileInputStream(file);
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        IOUtils.copy(is, response.getOutputStream());
        response.flushBuffer();
    }
}
