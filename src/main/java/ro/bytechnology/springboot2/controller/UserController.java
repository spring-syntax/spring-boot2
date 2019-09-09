package ro.bytechnology.springboot2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ro.bytechnology.springboot2.db.entities.User;
import ro.bytechnology.springboot2.service.UserService;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all_users")
    public List<User> displayUsers(){
        return userService.findAll();
    }

    @GetMapping(value= "/admins",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> displayAdmins(){
        return userService.findAdmins();
    }
}
