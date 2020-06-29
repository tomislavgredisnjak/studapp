package hr.tvz.gredisnjak.studapp;

import java.util.List;

public interface CourseService {

    List<CourseDTO> findAll();

    List<CourseDTO> findCourseByJmbag(String JMBAG);

}
