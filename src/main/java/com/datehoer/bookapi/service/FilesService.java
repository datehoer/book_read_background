package com.datehoer.bookapi.service;

import java.util.List;

public interface FilesService {
    List<String> list(String filePath);
    String readFile(String filePath, String fileName);
    boolean deleteFile(String filePath, String fileName);
    boolean moveFile(String filePath, String fileName, String newFilePath);
    boolean moveFiles(String filePath, List<String> fileNames, String newFilePath);
    boolean executeShScript(String scriptPath, String scriptName);
}
