package se.mbaeumer.glucometriq.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.mbaeumer.glucometriq.models.GlucoseMeasurement;
import se.mbaeumer.glucometriq.models.User;
import se.mbaeumer.glucometriq.models.UserType;
import se.mbaeumer.glucometriq.repositories.GlucoseMeasurementRepository;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * Created by martinbaumer on 27/07/16.
 */
@RestController
@RequestMapping("/myglucose")
public class GlucoseMeasurementController {
    private GlucoseMeasurementRepository glucoseMeasurementRepository;

    @Autowired
    public GlucoseMeasurementController(GlucoseMeasurementRepository repository){
        this.glucoseMeasurementRepository = repository;
    }

    @RequestMapping(method= RequestMethod.GET)
    public List<GlucoseMeasurement> getAllGlucoseMeasurements(Model model) {
        System.out.println("...in GlucoseMeasurementController");
        List<GlucoseMeasurement> glucoseMeasurements = glucoseMeasurementRepository.findAll();
        if (glucoseMeasurements != null){
            System.out.println("...in GlucoseMeasurementController - found some values");
            System.out.println("Number of measurements: " + glucoseMeasurements.size());
            for  (GlucoseMeasurement item : glucoseMeasurements){
                System.out.println("glucose level: " + item.getGlucoseValue());
            }
            model.addAttribute("glucosevalues", glucoseMeasurements);
        }
        return glucoseMeasurements;
    }

    @RequestMapping(value="/get/{glucoseMeasurementId}", method= RequestMethod.GET)
    public GlucoseMeasurement getSingleGlucoseMeasurementByUser(@PathVariable int glucoseMeasurementId){
        GlucoseMeasurement glucoseMeasurement = glucoseMeasurementRepository.findSingleGlucoseMeasurementById(glucoseMeasurementId);
        if (glucoseMeasurement != null){
            System.out.println("...in getSingleGlucoseMeasurementByUser");
        }
        return glucoseMeasurement;
    }

    @RequestMapping(value="/user/{userId}", method= RequestMethod.GET)
    public List<GlucoseMeasurement> getGlucoseMeasurementByUser(@PathVariable int userId){
        User u = new User();
        u.setId(userId);
        List<GlucoseMeasurement> glucoseMeasurements = glucoseMeasurementRepository.findGlucoseMeasurementsByUser(u);
        if (glucoseMeasurements != null){
            System.out.println("...in GlucoseMeasurementControllerByUser - found some values");
            System.out.println("Number of measurements: " + glucoseMeasurements.size());
            for  (GlucoseMeasurement item : glucoseMeasurements){
                System.out.print("user: " + item.getUser().getUserName() + "   ");
                System.out.println("glucose level: " + item.getGlucoseValue());
            }
            //model.addAttribute("glucosevalues", glucoseMeasurements);
        }
        return glucoseMeasurements;
    }

    @RequestMapping(method=RequestMethod.POST)
    public GlucoseMeasurement create(@RequestBody GlucoseMeasurement glucoseMeasurement){
        return glucoseMeasurementRepository.save(glucoseMeasurement);
    }

    @RequestMapping(method=RequestMethod.PUT)
    public  GlucoseMeasurement update(@RequestBody GlucoseMeasurement glucoseMeasurement){
        System.out.println("...in GlucoseMeasurementControllerByUser - update");
        glucoseMeasurementRepository.save(glucoseMeasurement);
        return glucoseMeasurement;
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        System.out.println("...in GlucoseMeasurementControllerByUser - delete");
        GlucoseMeasurement glucoseMeasurement = new GlucoseMeasurement();
        glucoseMeasurement.setId(id);
        glucoseMeasurementRepository.delete(glucoseMeasurement);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
