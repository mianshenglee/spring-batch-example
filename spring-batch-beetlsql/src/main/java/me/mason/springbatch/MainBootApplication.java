package me.mason.springbatch;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 程序入口
 * @author  mason
 * @since  2019/6/1
 */
@SpringBootApplication
public class MainBootApplication {

	public static void main(String[] args) {
	    SpringApplication app = new SpringApplication(MainBootApplication.class);
	    app.setBannerMode(Mode.OFF);
	    app.run(args);
	}
}
