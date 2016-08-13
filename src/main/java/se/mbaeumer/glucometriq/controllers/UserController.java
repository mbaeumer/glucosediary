package se.mbaeumer.glucometriq.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.mbaeumer.glucometriq.models.Credentials;
import se.mbaeumer.glucometriq.models.User;
import se.mbaeumer.glucometriq.repositories.UserRepository;

import java.sql.SQLException;
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

    /*
    @RequestMapping(value="/login", method= RequestMethod.GET)
    public User authenticate(@RequestParam("username") String username, @RequestParam("password") String password){
        // localhost:9090/users/login?username=mba&password=nnn
        System.out.println(username);
        System.out.println(password);
        List<User> users = userRepository.findByUserNameAndPassword(username, password);
        if (users.size() > 0){
            System.out.println("user found");
        }else{
            System.out.println("no user found");

        }
        User user = null;
        return user;

    }
    */

    @RequestMapping(value="login", method=RequestMethod.POST)
    public User authenticate(@RequestBody Credentials credentials){
        User user = null;
        user = userRepository.findByUserNameAndPassword(credentials.getUsername(), credentials.getPassword());
        if (user != null){
            System.out.println("user found");
        }else{
            System.out.println("no user found");
            //throw new SQLException("");
        }
        return user;
    }
}
