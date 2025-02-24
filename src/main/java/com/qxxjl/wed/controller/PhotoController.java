package com.qxxjl.wed.controller;

import com.qxxjl.wed.core.domain.AjaxResult;
import com.qxxjl.wed.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

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
    public String getImageUrl() {
        String imageName = "OIP.jpg";
        String imagePath = "D:/java_project/images" + imageName;
        File imageFile = new File(imagePath);
        if (imageFile.exists()) {
            log.info("11");
            // 这里假设你的项目部署在本地 1000 端口，通过静态资源映射访问图片
            return "http://localhost:1000/java_project/images/" + imageName;
        }
        log.info("22");
        return "11";
    }
}
