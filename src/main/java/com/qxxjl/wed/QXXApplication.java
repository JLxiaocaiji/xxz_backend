package com.qxxjl.wed;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

@Slf4j
@MapperScan(value = "com.qxxjl.wed.mapper")
@EnableAsync
@SpringBootApplication
@EnableKnife4j
@EnableScheduling
@ServletComponentScan
public class QXXApplication {

    public static void main(String[] args) throws UnknownHostException {
//        final ConfigurableApplicationContext context = SpringApplication.run(QXXApplication.class, args);
//        try {
//            final NettyServer nettyServer = context.getBean(NettyServer.class);
//            nettyServer.starter();
//            log.info("netty 启动成功 port:{}",nettyServer.getPort());
//        } catch (Exception e) {
//            log.info("netty 启动失败");
//            e.printStackTrace();
//        }

        ConfigurableApplicationContext context = SpringApplication.run(QXXApplication.class, args);
        log.info(startAppLog(context, InetAddress.getLocalHost().getHostAddress()));
    }

    public static String startAppLog(ConfigurableApplicationContext application, String ip) {
        Environment env = application.getEnvironment();
        String port = Objects.toString(env.getProperty("server.port"), "");
        String path = Objects.toString(env.getProperty("server.servlet.context-path"), "");
        port = "80".equals(port)?"":":"+port;
        String startLog = "\n\t----------------------------------------------------------\n\t" +
                "Application " + Objects.toString(env.getProperty("spring.application.name"), "") + " is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost" + port + path + "\n\t" +
                "External: \thttp://" + ip + "" + port + path + "\n\t";
        startLog = startLog + "Knife4j:  \thttp://"+ ip + port + path + "/doc.html\n\t";
        startLog = startLog + "----------------------------------------------------------";
        return startLog;
    }
}
