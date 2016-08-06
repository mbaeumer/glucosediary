package se.mbaeumer.glucometriq.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.mbaeumer.glucometriq.models.GlucoseMeasurement;
import se.mbaeumer.glucometriq.models.UserType;
import se.mbaeumer.glucometriq.repositories.GlucoseMeasurementRepository;

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
                System.out.println(item.getGlucoseValue());
            }
            model.addAttribute("glucosevalues", glucoseMeasurements);
        }
        return glucoseMeasurements;
    }

    @RequestMapping(method=RequestMethod.POST)
    public GlucoseMeasurement create(@RequestBody GlucoseMeasurement glucoseMeasurement){
        return glucoseMeasurementRepository.save(glucoseMeasurement);

    }

}
