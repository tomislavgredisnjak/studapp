package hr.tvz.gredisnjak.studapp;

import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.util.*;

@Repository
class MockStudentRepository implements StudentRepository {

    private List<Student> MOCKED_STUDENTS = new ArrayList<Student>(Arrays.asList(
            new Student("Ivo", "Ivić", LocalDate.now().minusYears(27),"0256423323", 120),
            new Student("Lucija", "Lucić", LocalDate.now().minusYears(25),"0256423324", 98)
    ));

    @Override
    public List<Student> findAll(){return MOCKED_STUDENTS;}

    @Override
    public Optional<Student> findStudentByJMBAG(final String JMBAG){
        return MOCKED_STUDENTS.stream().filter(it -> Objects.equals(it.getJmbag(), JMBAG)).findAny();
    }
    @Override
    public Optional<Student> edit(StudentCommand command) {
        return Optional.empty();
    }
    @Override
    public Optional<Student> save(StudentCommand command){
        Student addedStudent = new Student(command.getFirstName(), command.getLastName(), command.getDateOfBirth(),
                command.getJmbag(), command.getNumberOfECTS());
        MOCKED_STUDENTS.add(addedStudent);
        return MOCKED_STUDENTS.stream().filter(it -> Objects.equals(it.getJmbag(), command.getJmbag())).findAny();
    }
    @Override
    public void deleteByJMBAG(String JMBAG) {
        if(findStudentByJMBAG(JMBAG).isPresent()) {
            MOCKED_STUDENTS.remove(findStudentByJMBAG(JMBAG).get());}
    }
}
