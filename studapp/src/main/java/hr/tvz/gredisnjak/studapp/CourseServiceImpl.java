package hr.tvz.gredisnjak.studapp;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class CourseServiceImpl implements CourseService {

    private final JpaCourseRepository courseRepository;

    public CourseServiceImpl(JpaCourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    @Override
    public List<CourseDTO> findAll(){

        return courseRepository.findAll().stream().map(this::mapCourseToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseDTO> findCourseByJmbag(final String jmbag){

        return courseRepository.findByStudents_JMBAG(jmbag).stream().map(this::mapCourseToDTO).collect(Collectors.toList());

    }

    private CourseDTO mapCourseToDTO(final Course course){
        return new CourseDTO(course.getName(), course.getNumberOfECTS());
    }

}
