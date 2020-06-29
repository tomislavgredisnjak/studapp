package hr.tvz.gredisnjak.studapp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("student")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {


    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN", "ROLE_DELETER"})
    @GetMapping
    public List<StudentDTO> getAllStudents(){
        return studentService.findAll();
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN", "ROLE_DELETER"})
    @GetMapping("/{jmbag}")
    public Optional<StudentDTO> getStudentByJMBAG(@PathVariable String jmbag){

        return studentService.findStudentByJmbag(jmbag);
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<StudentDTO> save(@Valid @RequestBody final StudentCommand command) {
        command.setDateOfBirth(LocalDate.now().minusYears(25));
        return studentService.save(command).map(
                studentDTO -> ResponseEntity.status(HttpStatus.CREATED).body(studentDTO)
        ).orElseGet(
                () -> ResponseEntity.status(HttpStatus.CONFLICT).build()
        );
    }

    @Secured({"ROLE_ADMIN", "ROLE_DELETER"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{JMBAG}")
    public void delete(@PathVariable String JMBAG){
        studentService.deleteByJMBAG(JMBAG);
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{jmbag}")
    public ResponseEntity<StudentDTO> edit(@Valid @RequestBody final StudentCommand command) {

        return studentService.edit(command).map(
                studentDTO -> ResponseEntity.status(HttpStatus.CREATED).body(studentDTO)
        ).orElseGet(
                () -> ResponseEntity.status(HttpStatus.CONFLICT).build()
        );
    }
}
