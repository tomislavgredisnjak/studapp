package hr.tvz.gredisnjak.studapp;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

interface StudentRepository {

    List<Student> findAll();

    Optional<Student> findStudentByJMBAG(String JMBAG);

    Optional<Student> edit(StudentCommand command);

    Optional<Student> save(StudentCommand command);

    void deleteByJMBAG(String JMBAG);


}
