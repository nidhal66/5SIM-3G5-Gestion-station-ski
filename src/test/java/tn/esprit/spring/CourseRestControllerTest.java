package tn.esprit.spring;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import tn.esprit.spring.controllers.CourseRestController;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.services.ICourseServices;

public class CourseRestControllerTest {

    @InjectMocks
    private CourseRestController courseRestController;

    @Mock
    private ICourseServices courseServices;

    private Course course;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        course = new Course();
        course.setId(1L);
        course.setName("Sample Course");
    }

    @Test
    public void testAddCourse() {
        when(courseServices.addCourse(any(Course.class))).thenReturn(course);

        Course result = courseRestController.addCourse(course);

        verify(courseServices, times(1)).addCourse(any(Course.class));
        assert result != null;
        assert result.getId().equals(1L);
        assert result.getName().equals("Sample Course");
    }

    @Test
    public void testGetAllCourses() {
        List<Course> courses = new ArrayList<>();
        courses.add(course);
        when(courseServices.retrieveAllCourses()).thenReturn(courses);

        List<Course> result = courseRestController.getAllCourses();

        verify(courseServices, times(1)).retrieveAllCourses();
        assert result != null;
        assert result.size() == 1;
        assert result.get(0).getName().equals("Sample Course");
    }

    @Test
    public void testUpdateCourse() {
        when(courseServices.updateCourse(any(Course.class))).thenReturn(course);

        Course result = courseRestController.updateCourse(course);

        verify(courseServices, times(1)).updateCourse(any(Course.class));
        assert result != null;
        assert result.getId().equals(1L);
        assert result.getName().equals("Sample Course");
    }

    @Test
    public void testGetById() {
        when(courseServices.retrieveCourse(any(Long.class))).thenReturn(course);

        Course result = courseRestController.getById(1L);

        verify(courseServices, times(1)).retrieveCourse(any(Long.class));
        assert result != null;
        assert result.getId().equals(1L);
        assert result.getName().equals("Sample Course");
    }
}
