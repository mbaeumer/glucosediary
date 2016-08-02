package se.mbaeumer.glucometriq.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.mbaeumer.glucometriq.models.UserType;
import se.mbaeumer.glucometriq.repositories.UserTypeRepository;

import java.util.List;

/**
 * Created by martinbaumer on 31/07/16.
 */
@RestController
@RequestMapping("/usertypes")
public class UserTypeController {
    private UserTypeRepository userTypeRepository;

    @Autowired
    public UserTypeController(UserTypeRepository userTypeRepository){
        System.out.println("...in UserTypeController - constructor");
        this.userTypeRepository = userTypeRepository;
    }

    @RequestMapping(method= RequestMethod.GET)
    public List<UserType> getUserTypes(Model model) {
        System.out.println("...in UserTypeController - getUserTypes");
        List<UserType> userTypes = userTypeRepository.findAll();
        if (userTypes != null){
            System.out.println("...in UserTypeController - found some user types");
            System.out.println("Number of user types: " + userTypes.size());
            for  (UserType userType : userTypes){
                System.out.println(userType.getName());
            }
            model.addAttribute("entries", userTypes);
        }
        return userTypes;
    }
}
