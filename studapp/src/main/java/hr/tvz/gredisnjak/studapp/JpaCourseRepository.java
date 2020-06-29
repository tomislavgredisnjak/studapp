package hr.tvz.gredisnjak.studapp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaCourseRepository extends JpaRepository<Course, Integer> {

    List<Course> findAll();

    List<Course> findByStudents_JMBAG(String JMBAG);

}
