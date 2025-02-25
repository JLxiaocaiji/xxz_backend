package com.qxxjl.wed.utils.file;

public class file {

    public static boolean isImage(String fileName) {
        String[] imageExtensions = {".jpg", ".jpeg", ".png", ".gif", ".bmp", ".tiff"};
        for (String ext: imageExtensions) {
            if( fileName.toLowerCase().endsWith(ext)) {
                return true;
            }
        }
        return false;
    }
}
