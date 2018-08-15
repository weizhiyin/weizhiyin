package net.weizhiyin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("net.weizhiyin.*.mapper*")
public class WeizhiyinApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeizhiyinApplication.class, args);
    }
}
