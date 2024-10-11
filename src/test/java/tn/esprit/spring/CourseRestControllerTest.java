package tn.esprit.spring.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.services.ICourseServices;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CourseRestControllerTest {

    @InjectMocks
    private CourseRestController courseRestController;

    @Mock
    private ICourseServices courseServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCourse() {
        Course course = new Course();
        course.setId(1L); // Set the ID or other fields as necessary

        when(courseServices.addCourse(any(Course.class))).thenReturn(course);

        Course result = courseRestController.addCourse(course);

        assertEquals(course, result);
        verify(courseServices, times(1)).addCourse(course);
    }

    @Test
    void testGetAllCourses() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course());

        when(courseServices.retrieveAllCourses()).thenReturn(courses);

        List<Course> result = courseRestController.getAllCourses();

        assertEquals(courses, result);
        verify(courseServices, times(1)).retrieveAllCourses();
    }

    @Test
    void testUpdateCourse() {
        Course course = new Course();
        course.setId(1L); // Set the ID or other fields as necessary

        when(courseServices.updateCourse(any(Course.class))).thenReturn(course);

        Course result = courseRestController.updateCourse(course);

        assertEquals(course, result);
        verify(courseServices, times(1)).updateCourse(course);
    }

    @Test
    void testGetById() {
        Course course = new Course();
        course.setId(1L); // Set the ID or other fields as necessary

        when(courseServices.retrieveCourse(1L)).thenReturn(course);

        Course result = courseRestController.getById(1L);

        assertEquals(course, result);
        verify(courseServices, times(1)).retrieveCourse(1L);
    }
}
