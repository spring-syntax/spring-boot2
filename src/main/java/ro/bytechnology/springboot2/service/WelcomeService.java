package ro.bytechnology.springboot2.service;

import org.springframework.stereotype.Service;

@Service
public class WelcomeService {

    public String helloMesage(){
        return "Hello World!";
    }
}
