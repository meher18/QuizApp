package com.epam.config;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.epam")
public class Config {

    @Bean("scanner")
    public Scanner getScanner()
    {
        return new Scanner(System.in);
    }

    @Bean("bufferedReader")
    public BufferedReader getBufferedReader()
    {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
