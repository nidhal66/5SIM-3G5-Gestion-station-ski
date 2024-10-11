package tn.esprit.spring.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.services.ICourseServices;

import java.util.List;

@Tag(name = "\uD83D\uDCDA Course Management")
@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseRestController {
    
    private final ICourseServices courseServices;
    private static final Logger logger = LogManager.getLogger(Course.class);

    @Operation(description = "Add Course")
    @PostMapping("/add")
    public Course addCourse(@RequestBody Course course){
         logger.info("addCourse was called.");

        try {
            // Simulate some processing
            logger.debug("Processing data...");
            // Your processing logic here
         return  courseServices.addCourse(course);
        } catch (Exception e) {
            logger.error("An error occurred while processing data: ", e);
            return "Error!";
        }
    }

    @Operation(description = "Retrieve all Courses")
    @GetMapping("/all")
    public List<Course> getAllCourses(){
        logger.info("getAllCourses was called.");

        try {
            // Simulate some processing
            logger.debug("Processing data...");
            // Your processing logic here
            return courseServices.retrieveAllCourses();
        } catch (Exception e) {
            logger.error("An error occurred while processing data: ", e);
            return "Error!";
        }
    }

    @Operation(description = "Update Course ")
    @PutMapping("/update")
    public Course updateCourse(@RequestBody Course course){
          logger.info("updateCourse was called.");

        try {
            // Simulate some processing
            logger.debug("Processing data...");
            // Your processing logic here
          return  courseServices.updateCourse(course);
        } catch (Exception e) {
            logger.error("An error occurred while processing data: ", e);
            return "Error!";
        }
    }

    @Operation(description = "Retrieve Course by Id")
    @GetMapping("/get/{id-course}")
    public Course getById(@PathVariable("id-course") Long numCourse){
        return courseServices.retrieveCourse(numCourse);
    }

}
