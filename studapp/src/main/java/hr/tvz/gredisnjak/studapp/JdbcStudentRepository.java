package hr.tvz.gredisnjak.studapp;

import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;


@Repository
@Primary
public class JdbcStudentRepository implements StudentRepository {

    private JdbcTemplate jdbc;
    private SimpleJdbcInsert studentInserter;


    public JdbcStudentRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
        this.studentInserter = new SimpleJdbcInsert(jdbc).
                withTableName("Students");
    }

    @Override
    public List<Student> findAll(){
        return jdbc.query("select jmbag, firstName, lastName, dateOfBirth, numberOfEcts from Students",
                this::mapRowToStudent);
    }

    @Override
    public Optional<Student> findStudentByJMBAG(String jmbag) {
        String sql = "Select jmbag, firstName, lastName, dateOfBirth, numberOfEcts from Students where jmbag = ?";
        try {
            return Optional.of(jdbc.queryForObject(sql, this::mapRowToStudent, jmbag));
        }
        catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Student> edit(StudentCommand command) {

        StringBuilder sql = new StringBuilder();
        sql.append("Update Students SET firstName = '");
        sql.append(command.getFirstName());
        sql.append("', lastName = '");
        sql.append(command.getLastName());
        sql.append("', numberOfEcts = ");
        sql.append(command.getNumberOfECTS());
        sql.append(" where jmbag = '");
        sql.append(command.getJmbag());
        sql.append("'");
        try {
            jdbc.execute(sql.toString());
            return findStudentByJMBAG(command.getJmbag());
        }
        catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

    }

    @Override
    public Optional<Student> save(StudentCommand command){

        command.setDateOfBirth(LocalDate.now().minusYears(25));
        Student addedStudent = new Student(command.getFirstName(), command.getLastName(), command.getDateOfBirth(),
                command.getJmbag(), command.getNumberOfECTS());
        Map<String, Object> values = new HashMap<>();
        values.put("firstName", addedStudent.getName());
        values.put("lastName", addedStudent.getSurname());
        values.put("dateOfBirth", addedStudent.getDateOfBirth());
        values.put("jmbag", addedStudent.getJmbag());
        values.put("numberOfEcts", addedStudent.getNumberOfECTS());
        studentInserter.execute(values);
        return Optional.of(addedStudent);
    }

    @Override
    public void deleteByJMBAG(String JMBAG) {
        jdbc.update("DELETE from Students WHERE jmbag = ?",JMBAG);
    }


    private Student mapRowToStudent(ResultSet rs, int rowNum) throws SQLException{
        Student student = new Student();
        student.setJmbag(rs.getString("jmbag"));
        student.setName(rs.getString("firstName"));
        student.setSurname(rs.getString("lastName"));
        student.setNumberOfECTS(rs.getInt("numberOfEcts"));
        student.setDateOfBirth(LocalDate.parse(rs.getDate("dateOfBirth").toString()));
        student.getDateOfBirth();
        return student;
    }
}
