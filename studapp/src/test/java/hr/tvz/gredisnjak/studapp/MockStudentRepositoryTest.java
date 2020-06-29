package hr.tvz.gredisnjak.studapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MockStudentRepositoryTest {

    @Test
    void findAll() {
        List<Student> MOCKED_STUDENTS = new ArrayList<Student>(Arrays.asList(
                new Student("Ivo", "Ivić", LocalDate.now().minusYears(27),"0256423323", 120),
                new Student("Lucija", "Lucić", LocalDate.now().minusYears(25),"0256423324", 98)
        ));
        assertAll("MOCKED_STUDENTS",
                () -> assertEquals(MOCKED_STUDENTS.get(0).getJmbag(),"0256423323"),
                () -> assertEquals(MOCKED_STUDENTS.get(1).getJmbag(),"0256423324"));
    }



    @Test
    void save() {
        List<Student> MOCKED_STUDENTS = new ArrayList<Student>(Arrays.asList(
                new Student("Ivo", "Ivić", LocalDate.now().minusYears(27),"0256423323", 120),
                new Student("Lucija", "Lucić", LocalDate.now().minusYears(25),"0256423324", 98)
        ));
        Student addedStudent = new Student("Pero", "Markić", LocalDate.now(),
                "0245698324", 111);

        MOCKED_STUDENTS.add(addedStudent);

        assertEquals(MOCKED_STUDENTS.stream().filter(it -> Objects.equals(it.getJmbag(), "0245698324")).findAny(), Optional.of(addedStudent));
    }


}