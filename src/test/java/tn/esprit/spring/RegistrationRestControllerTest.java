package tn.esprit.spring;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tn.esprit.spring.controllers.RegistrationRestController;
import tn.esprit.spring.entities.Registration;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.services.IRegistrationServices;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(RegistrationRestController.class)
public class RegistrationRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IRegistrationServices registrationServices;

    private Registration registration;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        registration = new Registration();
        registration.setNumRegistration(1L);
        registration.setNumWeek(5);
        // Initialize other fields if necessary
    }

    // Test for adding and assigning to skier
    @Test
    public void testAddAndAssignToSkier() throws Exception {
        // Mock the service call
        when(registrationServices.addRegistrationAndAssignToSkier(any(Registration.class), anyLong()))
                .thenReturn(registration);

        mockMvc.perform(put("/registration/addAndAssignToSkier/{numSkieur}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registration)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numRegistration").value(1))
                .andExpect(jsonPath("$.numWeek").value(5));
    }

    // Test for assigning to a course
    @Test
    public void testAssignToCourse() throws Exception {
        // Mock the service call
        when(registrationServices.assignRegistrationToCourse(anyLong(), anyLong()))
                .thenReturn(registration);

        mockMvc.perform(put("/registration/assignToCourse/{numRegis}/{numCourse}", 1L, 2L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numRegistration").value(1))
                .andExpect(jsonPath("$.numWeek").value(5));
    }

    // Test for adding and assigning to skier and course
    @Test
    public void testAddAndAssignToSkierAndCourse() throws Exception {
        // Mock the service call
        when(registrationServices.addRegistrationAndAssignToSkierAndCourse(any(Registration.class), anyLong(), anyLong()))
                .thenReturn(registration);

        mockMvc.perform(put("/registration/addAndAssignToSkierAndCourse/{numSkieur}/{numCourse}", 1L, 2L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registration)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numRegistration").value(1))
                .andExpect(jsonPath("$.numWeek").value(5));
    }

    // Test for getting number of weeks for a course of instructor by support
    @Test
    public void testNumWeeksCourseOfInstructorBySupport() throws Exception {
        List<Integer> weeks = Arrays.asList(5, 8);

        // Mock the service call
        when(registrationServices.numWeeksCourseOfInstructorBySupport(anyLong(), any(Support.class)))
                .thenReturn(weeks);

        mockMvc.perform(get("/registration/numWeeks/{numInstructor}/{support}", 1L, "SKI")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value(5))
                .andExpect(jsonPath("$[1]").value(8));
    }
}


