package com.globallogic.refactoredpractice;

import com.globallogic.refactoredpractice.mapper.FileMapper;
import com.globallogic.refactoredpractice.properties.FileProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({FileProperties.class})
@SpringBootApplication
public class RefactoredPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(RefactoredPracticeApplication.class, args);
    }

}
