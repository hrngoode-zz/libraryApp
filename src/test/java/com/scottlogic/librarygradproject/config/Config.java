package com.scottlogic.librarygradproject.config;

import com.scottlogic.librarygradproject.repository.JpaRepo;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;

public class Config {

    @Bean
    public JpaRepo jpaRepo(){
        return Mockito.mock(JpaRepo.class);
    }
}
