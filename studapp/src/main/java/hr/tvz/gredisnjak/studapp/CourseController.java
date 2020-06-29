package hr.tvz.gredisnjak.studapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("course")
@CrossOrigin(origins = "http://localhost:4200")
public class CourseController {


    private final CourseService courseService;

    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN", "ROLE_DELETER"})
    @GetMapping
    public List<CourseDTO> getAllCourses(){
        return courseService.findAll();
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN", "ROLE_DELETER"})
    @GetMapping(params = "jmbag")
    public List<CourseDTO> getCourseByJmbag(@RequestParam final String jmbag){
        return courseService.findCourseByJmbag(jmbag);
    }
}
