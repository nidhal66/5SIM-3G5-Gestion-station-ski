package tn.esprit.spring.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.services.IInstructorServices;

import java.util.List;

@Tag(name = "\uD83D\uDC69\u200D\uD83C\uDFEB Instructor Management")
@RestController
@RequestMapping("/instructor")
@RequiredArgsConstructor
public class InstructorRestController {

    private final IInstructorServices instructorServices;
    private static final Logger logger = LogManager.getLogger(Instructor.class);

    @Operation(description = "Add Instructor")
    @PostMapping("/add")
    public Instructor addInstructor(@RequestBody Instructor instructor){
           logger.info("addInstructorMethod was called.");

        try {
            // Simulate some processing
            logger.debug("Processing data...");
            // Your processing logic here
        return  instructorServices.addInstructor(instructor);
        } catch (Exception e) {
            logger.error("An error occurred while processing data: ", e);
             throw new RuntimeException("Error adding Instractor: " + e.getMessage());
        }
    }    
    
    @Operation(description = "Add Instructor and Assign To Course")
    @PutMapping("/addAndAssignToCourse/{numCourse}")
    public Instructor addAndAssignToInstructor(@RequestBody Instructor instructor, @PathVariable("numCourse")Long numCourse){
              logger.info("addAndAssignToInstructorMethod was called.");

        try {
            // Simulate some processing
            logger.debug("Processing data...");
            // Your processing logic here
        return  instructorServices.addInstructorAndAssignToCourse(instructor,numCourse);
        } catch (Exception e) {
            logger.error("An error occurred while processing data: ", e);
             throw new RuntimeException("Error assigning Instractor: " + e.getMessage());
        }
    }    
    
    @Operation(description = "Retrieve all Instructors")
    @GetMapping("/all")
    public List<Instructor> getAllInstructors(){
                    logger.info("getAllInstructorMethod was called.");

        try {
            // Simulate some processing
            logger.debug("Processing data...");
            // Your processing logic here
        return instructorServices.retrieveAllInstructors();
        } catch (Exception e) {
            logger.error("An error occurred while processing data: ", e);
             throw new RuntimeException("Error getting Instractor: " + e.getMessage());
        }
    }    
    

    @Operation(description = "Update Instructor ")
    @PutMapping("/update")
    public Instructor updateInstructor(@RequestBody Instructor Instructor){
                  logger.info("updateInstructorMethod was called.");

        try {
            // Simulate some processing
            logger.debug("Processing data...");
            // Your processing logic here
        return  instructorServices.updateInstructor(Instructor);
        } catch (Exception e) {
            logger.error("An error occurred while processing data: ", e);
             throw new RuntimeException("Error updating Instractor: " + e.getMessage());
        }
    }    
    

    @Operation(description = "Retrieve Instructor by Id")
    @GetMapping("/get/{id-instructor}")
    public Instructor getById(@PathVariable("id-instructor") Long numInstructor){
        return instructorServices.retrieveInstructor(numInstructor);
    }

}
