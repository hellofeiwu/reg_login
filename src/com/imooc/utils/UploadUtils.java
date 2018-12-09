package com.imooc.utils;

import java.util.UUID;

/**
 * Created by feiwu on 12/9/18.
 */
public class UploadUtils {
    public static String getUUIDFileName(String fileName) {
        int index = fileName.lastIndexOf(".");
        String extention = fileName.substring(index);
        String uuidFileName = UUID.randomUUID().toString().replace("-", "") + extention;
        return uuidFileName;
    }
}
