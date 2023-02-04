package com.dzidziguri.springdatajpa.springdatajpa;

import com.dzidziguri.springdatajpa.springdatajpa.entities.Course;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.engine.support.discovery.SelectorResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.*;

import static org.junit.jupiter.api.Assertions.*;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SpringBootTest
class SpringDataJpaApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void givenCreateCourseWhenLoadTheCourseThenExpectSameCourse(){
        Course course = new Course("Rapid Spring Boot Application Development", "Spring", 4, "'Spring Boot gives all the power of the Spring Framework without all of the complexities");
        Course savedCourse = courseRepository.save(course);
        MatcherAssert.assertThat(courseRepository.findById(savedCourse.getId()).get().getName(), is(equalTo(course.getName())));
    }

    @Test
    public void givenUpdateCourseWhenLoadTheCourseThenExpectSameCourse(){
        Course course = new Course("Rapid Spring Boot Application Development", "Spring", 4, "'Spring Boot gives all the power of the Spring Framework without all of the complexities");
        Course savedCourse = courseRepository.save(course);
        course.setRating(18);
        Course updatedCourse = courseRepository.save(course);
        MatcherAssert.assertThat(courseRepository.findById(savedCourse.getId()).get().getRating(), is(equalTo(18)));
    }

    @Test
    public void givenDeleteCourseWhenDeleteCourseThenExpectItemIsNotPresent(){
        Course course = new Course("Rapid Spring Boot Application Development", "Spring", 4, "'Spring Boot gives all the power of the Spring Framework without all of the complexities");
        Course savedCourse = courseRepository.save(course);
        courseRepository.deleteById(savedCourse.getId());
        MatcherAssert.assertThat(courseRepository.findById(savedCourse.getId()).isPresent(), is(false));
    }

    @Test
    public void whenCountAllCoursesThenExpectFiveCourses() throws SQLException {
        ResultSet rs = null;
        int noOfCourses = 0;
        try (PreparedStatement ps = dataSource.getConnection().prepareStatement("SELECT COUNT(1) FROM COURSES")) {
            rs = ps.executeQuery();
            while (rs.next()) {
                noOfCourses = rs.getInt(1);
            }
            MatcherAssert.assertThat(noOfCourses, is(equalTo(5)));
        }
        finally {
            if (rs != null) {
                rs.close();
            }
    }

    }
    @Test
    void contextLoads() {
    }
}
