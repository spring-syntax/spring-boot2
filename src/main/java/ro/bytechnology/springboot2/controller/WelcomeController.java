package ro.bytechnology.springboot2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.bytechnology.springboot2.service.WelcomeService;

@RestController
public class WelcomeController {

    @Autowired
    private WelcomeService welcomeService;
    @RequestMapping(value = "/welcome",method = RequestMethod.GET,produces = MediaType.TEXT_PLAIN_VALUE)
    public String hello(){
        return welcomeService.helloMesage();
    }
}
