package xyz.n490808114.shopWeb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@MapperScan("xyz.n490808114.shopWeb.dao")
public class ShopWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopWebApplication.class, args);
	}

}
