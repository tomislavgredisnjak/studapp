package hr.tvz.gredisnjak.studapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;



import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    static final String TEST_FIRST_NAME = "Marko";
    static final String TEST_LAST_NAME = "Perković";
    static final String TEST_JMBAG = "0246587935";
    static final Integer TEST_NUMBER_OF_ECTS = 110;

    static final String TEST_JMBAG_GET = "0246587936";

    static final String TEST_JMBAG_DELETE = "0246587937";

    static final String TEST_JMBAG_EDIT = "0246587938";
    static final String TEST_FIRST_NAME_EDIT = "Ivan";

    static final String TEST_JMBAG_EDIT_CONFLICT = "0245114127";

    static final String TEST_JMBAG_EDIT_WRONG = "0246589s";
    static final Integer TEST_NUMBER_OF_ECTS_WRONG = 485;
    @Test
    void getAllStudents() throws Exception{

        this.mockMvc.perform(
                get("/student").with(user("admin")
                .password("test").roles("ADMIN")).with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    void getStudentByJMBAG() throws Exception {

        StudentCommand studentCommand = new StudentCommand();
        studentCommand.setFirstName(TEST_FIRST_NAME);
        studentCommand.setLastName(TEST_LAST_NAME);
        studentCommand.setJmbag(TEST_JMBAG_GET);
        studentCommand.setNumberOfECTS(TEST_NUMBER_OF_ECTS);

        ObjectMapper objectMapper = new ObjectMapper();

        this.mockMvc.perform(
                post("/student").with(user("admin")
                        .password("test").roles("ADMIN"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentCommand))
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.jmbag").value(TEST_JMBAG_GET))
                .andExpect(jsonPath("$.firstName").value(TEST_FIRST_NAME))
                .andExpect(jsonPath("$.lastName").value(TEST_LAST_NAME));

        this.mockMvc.perform(
                get("/student/" + TEST_JMBAG_GET).with(user("admin")
                        .password("test").roles("ADMIN"))
                        .with(csrf()).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.jmbag").value(TEST_JMBAG_GET));
    }


    @Test
    void save() throws Exception {
        StudentCommand studentCommand = new StudentCommand();
        studentCommand.setFirstName(TEST_FIRST_NAME);
        studentCommand.setLastName(TEST_LAST_NAME);
        studentCommand.setJmbag(TEST_JMBAG);
        studentCommand.setNumberOfECTS(TEST_NUMBER_OF_ECTS);

        ObjectMapper objectMapper = new ObjectMapper();

        this.mockMvc.perform(
                post("/student").with(user("admin")
                        .password("test").roles("ADMIN"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentCommand))
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.jmbag").value(TEST_JMBAG))
                .andExpect(jsonPath("$.firstName").value(TEST_FIRST_NAME))
                .andExpect(jsonPath("$.lastName").value(TEST_LAST_NAME));

        this.mockMvc.perform(
                post("/student").with(user("admin")
                        .password("test").roles("ADMIN"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentCommand))
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isConflict());

    }


    @Test
    void delete() throws Exception {

        StudentCommand studentCommand = new StudentCommand();
        studentCommand.setFirstName(TEST_FIRST_NAME);
        studentCommand.setLastName(TEST_LAST_NAME);
        studentCommand.setJmbag(TEST_JMBAG_DELETE);
        studentCommand.setNumberOfECTS(TEST_NUMBER_OF_ECTS);

        ObjectMapper objectMapper = new ObjectMapper();

        this.mockMvc.perform(
                post("/student").with(user("admin")
                        .password("test").roles("ADMIN"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentCommand))
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.jmbag").value(TEST_JMBAG_DELETE))
                .andExpect(jsonPath("$.firstName").value(TEST_FIRST_NAME))
                .andExpect(jsonPath("$.lastName").value(TEST_LAST_NAME));


        this.mockMvc.perform(
                org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("/student/" + TEST_JMBAG_DELETE).
                with(user("admin").password("test").roles("ADMIN")).with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent());

    }




    @Test
    void edit() throws Exception {

        StudentCommand studentCommand = new StudentCommand();
        studentCommand.setFirstName(TEST_FIRST_NAME);
        studentCommand.setLastName(TEST_LAST_NAME);
        studentCommand.setJmbag(TEST_JMBAG_EDIT);
        studentCommand.setNumberOfECTS(TEST_NUMBER_OF_ECTS);

        ObjectMapper objectMapper = new ObjectMapper();

        this.mockMvc.perform(
                post("/student").with(user("admin")
                        .password("test").roles("ADMIN"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentCommand))
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.jmbag").value(TEST_JMBAG_EDIT))
                .andExpect(jsonPath("$.firstName").value(TEST_FIRST_NAME))
                .andExpect(jsonPath("$.lastName").value(TEST_LAST_NAME));

        StudentCommand studentCommandEdit = new StudentCommand();
        studentCommandEdit.setFirstName(TEST_FIRST_NAME_EDIT);
        studentCommandEdit.setLastName(TEST_LAST_NAME);
        studentCommandEdit.setJmbag(TEST_JMBAG_EDIT);
        studentCommandEdit.setNumberOfECTS(TEST_NUMBER_OF_ECTS);

        this.mockMvc.perform(
                org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put("/student/" + TEST_JMBAG_EDIT).
                with(user("admin").password("test").roles("ADMIN")).with(csrf()).
                contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(studentCommandEdit))
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated())
         .andExpect(content().contentType(MediaType.APPLICATION_JSON))
         .andExpect(jsonPath("$.firstName").value(TEST_FIRST_NAME_EDIT));

        StudentCommand studentCommandEditConflict = new StudentCommand();
        studentCommandEditConflict.setFirstName(TEST_FIRST_NAME_EDIT);
        studentCommandEditConflict.setLastName(TEST_LAST_NAME);
        studentCommandEditConflict.setJmbag(TEST_JMBAG_EDIT_CONFLICT);
        studentCommandEditConflict.setNumberOfECTS(TEST_NUMBER_OF_ECTS);

        this.mockMvc.perform(
                org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put("/student/" + TEST_JMBAG_EDIT).
                        with(user("admin").password("test").roles("ADMIN")).with(csrf()).
                        contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(studentCommandEditConflict))
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isConflict());

        StudentCommand studentCommandEditWrong = new StudentCommand();
        studentCommandEditWrong.setFirstName(TEST_FIRST_NAME_EDIT);
        studentCommandEditWrong.setLastName(TEST_LAST_NAME);
        studentCommandEditWrong.setJmbag(TEST_JMBAG_EDIT_WRONG);
        studentCommandEditWrong.setNumberOfECTS(TEST_NUMBER_OF_ECTS_WRONG);

        this.mockMvc.perform(
                org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put("/student/" + TEST_JMBAG_EDIT).
                        with(user("admin").password("test").roles("ADMIN")).with(csrf()).
                        contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(studentCommandEditWrong))
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());



    }
}