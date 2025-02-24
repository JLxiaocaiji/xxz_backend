package com.qxxjl.wed;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class QXXServletInitialize extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        // 表明 SpringApplicationBuilder应该使用哪个类作为Spring应用程序的源
        // 基于 RuoYiApplication 这个主配置类来加载和初始化整个应用的配置、组件等，使得应用能够正确启动并提供相应的 Web 服务
        return application.sources(QXXApplication.class);
    }
}
