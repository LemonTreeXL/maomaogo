package cn.itsource.maomaogo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 测试 git是否安装成功
 */
@SpringBootApplication
@EnableConfigServer // 表示这是一个 配置中心服务器
public class ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class,args);
    }
}
