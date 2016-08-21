package se.mbaeumer.glucometriq.se.mbaeumer.glucometriq.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import se.mbaeumer.glucometriq.GlucosediaryApplication;
import se.mbaeumer.glucometriq.repositories.GlucoseMeasurementRepository;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by martinbaumer on 20/08/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GlucosediaryApplication.class)
@WebAppConfiguration
public class GlucoseMeasurementControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private GlucoseMeasurementRepository glucoseMeasurementRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp(){
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getGlucoseMeasurementsByUser() throws Exception{
        mockMvc.perform(get("/myglucose/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    public void getGlucoseMeasurementsByUserWithUnknownUserId() throws Exception{
        mockMvc.perform(get("/myglucose/user/4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void getGlucoseMeasurementsByUserWithInvalidUserId() throws Exception{
        mockMvc.perform(get("/myglucose/user/mba"))
                .andExpect(status().isBadRequest());
    }
}
