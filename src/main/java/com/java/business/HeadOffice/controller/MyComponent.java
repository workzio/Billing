package com.java.business.HeadOffice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyComponent {

    @Value("${app.username}")
    private String username;

    @Value("${app.password}")
    private String password;

}
