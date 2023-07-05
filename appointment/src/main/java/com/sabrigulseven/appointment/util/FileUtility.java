package com.sabrigulseven.appointment.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtility {
    private static final String UPLOAD_DIRECTORY = "pdf-files";

    public String saveFile(MultipartFile file) throws IOException {
        String uploadPath = getUploadPath();
        File directory = new File(uploadPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File newFile = new File(directory.getAbsolutePath() + File.separator + file.getOriginalFilename());
        FileOutputStream fileOutputStream = new FileOutputStream(newFile);
        FileCopyUtils.copy(file.getInputStream(), fileOutputStream);
        fileOutputStream.close();

        return newFile.getAbsolutePath();
    }

    public boolean isPdfFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && contentType.equalsIgnoreCase("application/pdf");
    }

    public void deleteFile(String filePath) {
        File file = new File(filePath);
        file.delete();
    }

    public String getUploadPath() {
        return UPLOAD_DIRECTORY;
    }

    public void sendFileResponse(String filePath, HttpServletResponse response) throws IOException {
        File file = new File(filePath);
        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "inline; filename=" + file.getName());
        response.setContentLength((int) file.length());
        Files.copy(file.toPath(), response.getOutputStream());
        response.getOutputStream().flush();
    }

}

