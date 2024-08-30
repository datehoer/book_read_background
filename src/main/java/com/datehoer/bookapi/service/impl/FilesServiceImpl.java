package com.datehoer.bookapi.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RuntimeUtil;
import com.datehoer.bookapi.service.FilesService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilesServiceImpl implements FilesService {

    private boolean isDirectoryMissing(String path) {
        return !FileUtil.isDirectory(path);
    }

    private boolean isFileMissing(String path) {
        return !FileUtil.isFile(path);
    }

    private String getFilePath(String filePath, String fileName){
        return filePath + File.separator + fileName;
    }

    @Override
    public List<String> list(String filePath) {
        if (isDirectoryMissing(filePath)) {
            return Collections.emptyList();
        }
        return FileUtil.listFileNames(filePath);
    }

    @Override
    public String readFile(String filePath, String fileName) {
        String fullPath = getFilePath(filePath, fileName);
        if (isFileMissing(fullPath)) {
            return null;
        }
        return FileUtil.readString(fullPath, StandardCharsets.UTF_8);
    }

    @Override
    public boolean deleteFile(String filePath, String fileName) {
        String fullPath = getFilePath(filePath, fileName);
        return FileUtil.exist(fullPath) && FileUtil.del(fullPath);
    }

    @Override
    public boolean moveFile(String filePath, String fileName, String newFilePath) {
        String sourcePath = getFilePath(filePath, fileName);
        String destinationPath = getFilePath(newFilePath, fileName);
        if (isFileMissing(sourcePath) || isDirectoryMissing(newFilePath)) {
            return false;
        }
        FileUtil.move(new File(sourcePath), new File(destinationPath), true);
        return true;
    }

    @Override
    public boolean moveFiles(String filePath, List<String> fileNames, String newFilePath) {
        if (isDirectoryMissing(newFilePath)) {
            return false;
        }
        List<Boolean> results = fileNames.stream()
                .map(fileName -> moveFile(filePath, fileName, newFilePath))
                .collect(Collectors.toList());
        return !results.contains(false);
    }

    @Override
    public boolean executeShScript(String scriptPath, String scriptName) {
        String fullPath = getFilePath(scriptPath, scriptName);
        if (isFileMissing(fullPath)) {
            return false;
        }
        String result = RuntimeUtil.execForStr(fullPath);
        return result != null;
    }
}