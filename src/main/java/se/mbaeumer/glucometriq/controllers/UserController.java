package se.mbaeumer.glucometriq.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.mbaeumer.glucometriq.models.User;
import se.mbaeumer.glucometriq.models.UserType;
import se.mbaeumer.glucometriq.repositories.UserRepository;
import se.mbaeumer.glucometriq.repositories.UserTypeRepository;

import java.util.List;

/**
 * Created by martinbaumer on 27/07/16.
 */
@RestController
@RequestMapping("/users")
public class UserController {
    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @RequestMapping(method= RequestMethod.GET)
    public List<User> getUsers(Model model) {
        List<User> users = userRepository.findAll();
        if (users != null){
            System.out.println("...in UserController - found some users");
            System.out.println("Number of user types: " + users.size());
            for  (User user : users){
                System.out.println(user.getUserName());
            }
            model.addAttribute("entries", users);
        }
        return users;
    }
}
