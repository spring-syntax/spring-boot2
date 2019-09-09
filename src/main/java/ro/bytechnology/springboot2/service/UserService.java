package ro.bytechnology.springboot2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.bytechnology.springboot2.db.entities.User;
import ro.bytechnology.springboot2.db.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public List<User> findAdmins(){
        return userRepository.findByRoleIgnoreCase("Admin");
    }
}
