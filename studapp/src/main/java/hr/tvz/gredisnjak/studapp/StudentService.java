package hr.tvz.gredisnjak.studapp;

import org.springframework.http.ResponseEntity;

import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<StudentDTO> findAll();

    Optional<StudentDTO> findStudentByJmbag(String JMBAG);

    Optional<StudentDTO> save(StudentCommand command);

    Optional<StudentDTO> edit(StudentCommand command);

    void deleteByJMBAG(String JMBAG);

}
