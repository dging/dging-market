package com.dging.dgingmarket.util;

import com.dging.dgingmarket.exception.InvalidFileException;
import com.dging.dgingmarket.domain.user.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtils {

    public static String userFilePathFrom(String basePath, String fileName) {
        User user = EntityUtils.userThrowable();
        return user.getUserId() + "/" + basePath + "/" + fileName;
    }

    public static String generateRandomFileNameWith(MultipartFile file) {

        String originalFilename = file.getOriginalFilename();

        if(originalFilename == null) {
            throw InvalidFileException.EXCEPTION;
        }

        String ext = fileExtensionFrom(originalFilename);

        return RandomStringUtils.random(20, true, true) + "." + ext;
    }

    public static String filenameFrom(String filePath) {
        String[] tokens = filePath.split("/");
        return tokens[tokens.length - 1];
    }

    public static String fileExtensionFrom(String filePath) {
        String[] tokens = filePath.split("\\.");
        return tokens[tokens.length - 1];
    }
}
