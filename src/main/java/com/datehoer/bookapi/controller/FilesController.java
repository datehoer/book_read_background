package com.datehoer.bookapi.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.datehoer.bookapi.common.PublicResponse;
import com.datehoer.bookapi.service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FilesController {
    @Autowired
    private FilesService filesService;
    @Value("${my-background.directory-path}")
    private String directoryPath;
    @Value("${my-background.script-name}")
    private String scriptName;
    @Value("${my-background.script-path}")
    private String scriptPath;
    @Value("${my-background.move-path}")
    private String movePath;
    private String getAbsolutePath(String relativePath) {
        Path currentPath = Paths.get(System.getProperty("user.dir"));
        Path fullPath = currentPath.resolve(relativePath).normalize();
        return fullPath.toString();
    }
    @SaCheckLogin
    @GetMapping("/list")
    public PublicResponse<List<String>> list() {
        return PublicResponse.success(filesService.list(getAbsolutePath(directoryPath)));
    }
    @SaCheckLogin
    @GetMapping("/readFile")
    public PublicResponse<String> readFile(@RequestParam String fileName) {
        return PublicResponse.success(filesService.readFile(getAbsolutePath(directoryPath), fileName));
    }
    @SaCheckLogin
    @DeleteMapping("/deleteFile")
    public PublicResponse<Boolean> deleteFile(@RequestParam String fileName) {
        return PublicResponse.success(filesService.deleteFile(getAbsolutePath(directoryPath), fileName));
    }
    @SaCheckLogin
    @PostMapping("/move")
    public PublicResponse<Boolean> move(@RequestBody List<String> fileNames) {
        return PublicResponse.success(filesService.moveFiles(getAbsolutePath(directoryPath), fileNames, getAbsolutePath(movePath)));
    }
    @SaCheckLogin
    @PostMapping("/executeScript")
    public PublicResponse<Boolean> executeScript() {
        return PublicResponse.success(filesService.executeShScript(getAbsolutePath(scriptPath), scriptName));
    }
}
