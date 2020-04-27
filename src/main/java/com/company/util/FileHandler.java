package com.company.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHandler {

    private String filePath;
    private String content;
    private String OpenedFile;

    public FileHandler(String filePath) {
        setFilePath(filePath);
    }

    public FileHandler() {}

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }





    public void setOpenedFile(String textFile) {
        this.OpenedFile = textFile;
    }

    public String openhandle(String openedFile) {
        try {
            content = Files.readString(Paths.get(openedFile));
        }
        catch(IOException e) {
            System.out.println("IOException!");
            e.printStackTrace();
        }
        return content;
    }

    // Saves the content to given file path
    public void save(String content) {

    }
}

