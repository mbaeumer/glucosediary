package se.mbaeumer.glucometriq.se.mbaeumer.glucometriq.controllers;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import se.mbaeumer.glucometriq.GlucosediaryApplication;
import se.mbaeumer.glucometriq.models.GlucoseMeasurement;
import se.mbaeumer.glucometriq.models.User;
import se.mbaeumer.glucometriq.repositories.GlucoseMeasurementRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    private GlucoseMeasurementRepository glucoseMeasurementRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private GlucoseMeasurement newGlucose;

    private User user;

    private HttpMessageConverter httpMessageConverter;

    @Autowired
    private void setConverters(HttpMessageConverter<?>[] converters){
        this.httpMessageConverter = Arrays.asList(converters).stream().filter(
                hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

        Assert.assertNotNull("httpMessageConverter must not be null", this.httpMessageConverter);
    }

    @Before
    public void setUp(){
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Ignore
    @Test
    public void getGlucoseMeasurementsByUser() throws Exception{
        mockMvc.perform(get("/myglucose/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].glucoseValue", is(5.4)))
                .andExpect(jsonPath("$[0].comment", equalTo("test comment")));
                //.andExpect(jsonPath("$", hasSize(4)));
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

    @Ignore
    @Test
    public void updateGlucoseMeasurementsByUser() throws Exception{
        mockMvc.perform(put("/myglucose/user/1"))
                .andExpect(status().isOk());
    }

    @Ignore
    @Test
    public void createGlucoseMeasurement() throws Exception{
        String glucoseMeasurementAsJson = json(initNewGlucose());
        mockMvc.perform(post("/myglucose")
                .contentType(contentType)
                .content(glucoseMeasurementAsJson))
                .andExpect(status().isOk());

        mockMvc.perform(get("/myglucose/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[4].glucoseValue", is(this.newGlucose.getGlucoseValue())))
                .andExpect(jsonPath("$", hasSize(5)));
    }

    private String json(Object o) throws IOException{
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.httpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

    private GlucoseMeasurement initNewGlucose(){
        newGlucose = new GlucoseMeasurement();
        newGlucose.setGlucoseValue(new BigDecimal(8.9));
        newGlucose.setUser(getSampleUser());
        newGlucose.setMeasureDate(new java.util.Date());
        return newGlucose;
    }

    private User getSampleUser(){
        user = new User();
        user.setId(1);
        user.setFirstName("Martin");
        user.setLastName("Bäumer");
        return user;
    }
}
