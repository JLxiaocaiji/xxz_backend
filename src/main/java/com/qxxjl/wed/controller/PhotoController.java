package com.qxxjl.wed.controller;

import com.qxxjl.wed.core.domain.AjaxResult;
import com.qxxjl.wed.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.qxxjl.wed.utils.file.file.isImage;

@RestController
@RequestMapping
@Slf4j
public class PhotoController {

    @GetMapping("/init")
    public AjaxResult login() {
        log.info("用户登录： userLoginDTO:{}", 111);
        return AjaxResult.success("111");
    }

    @GetMapping("/getImage")
    public List<String> getImageUrl() {
        String picDirectoryPath = "D:/images/";
        File directory = new File(picDirectoryPath);

        List<String> imageUrls = new ArrayList<>();

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for( File file: files) {
                    if (file.isFile() && isImage(file.getName())) {
                        String imageName = file.getName();
                        String imageUrl = "http://localhost:1000/images/" + imageName;
                        imageUrls.add(imageUrl);
                    }
                }
            }
        }
        return imageUrls;
    }
}
