package kr.co.nexsys.mcp.homemanager;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class HomeManagerApplication {

    public static void main(String[] args) {

        SpringApplication.run(HomeManagerApplication.class, args);
    }

}
