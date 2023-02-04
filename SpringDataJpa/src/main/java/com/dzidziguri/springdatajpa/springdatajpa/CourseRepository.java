package com.dzidziguri.springdatajpa.springdatajpa;

import com.dzidziguri.springdatajpa.springdatajpa.entities.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
}
