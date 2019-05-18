package cn.itsource.maomaogo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient //表示是一个Eureka客户端
@EnableSwagger2 //标识使用swagger生成接口文档
@MapperScan("cn.itsource.maomaogo.mapper") //指定扫描那个包下面的类。防止不在同包或者子包下
//@ComponentScan("cn.itsource.maomaogo.client") // 扫描fegin接口
@EnableFeignClients(basePackages = "cn.itsource.maomaogo.client") //开启feign支持
public class ProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class,args);
    }

}
