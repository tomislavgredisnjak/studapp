package hr.tvz.gredisnjak.studapp;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class StudentServiceImpl implements StudentService {

    private static final int YEARS_AFTER_WHICH_TUITION_SHOUD_BE_PAYED = 26;

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentDTO> findAll(){

        return studentRepository.findAll().stream().map(this::mapStudentToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<StudentDTO> findStudentByJmbag(final String jmbag){

        if(studentRepository.findStudentByJMBAG(jmbag).isPresent()){
            return studentRepository.findStudentByJMBAG(jmbag).map(this::mapStudentToDTO);
        }else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<StudentDTO> save(StudentCommand command) {

        if(studentRepository.findStudentByJMBAG(command.getJmbag()).isPresent()){
            return Optional.empty();
        }else {
            return studentRepository.save(command).map(this::mapStudentToDTO);
        }
    }

    @Override
    public Optional<StudentDTO> edit(StudentCommand command) {

        if(studentRepository.findStudentByJMBAG(command.getJmbag()).isPresent()){
            return studentRepository.edit(command).map(this::mapStudentToDTO);
        }else {
            return Optional.empty();
        }
    }


    @Override
    public void deleteByJMBAG(String JMBAG) {
        studentRepository.deleteByJMBAG(JMBAG);
    }

    private StudentDTO mapStudentToDTO(final Student student){
        return new StudentDTO(student.getName(), student.getSurname(), student.getJmbag(), student.getNumberOfECTS(),
                shouldTuitionBePayed(student.getDateOfBirth()));
    }

    private boolean shouldTuitionBePayed(LocalDate dateOfBirth){
        return dateOfBirth.plusYears(YEARS_AFTER_WHICH_TUITION_SHOUD_BE_PAYED).isBefore(LocalDate.now());
    }
}
