package hr.tvz.gredisnjak.studapp;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User userNew = new User();

    @Test
    void setId() {
        userNew.setId(BigInteger.valueOf(6));
        assertEquals(userNew.getId(), BigInteger.valueOf(6));
    }

    @Test
    void setUsername() {
        userNew.setUsername("Petra123");
        assertEquals(userNew.getUsername(), "Petra123");
    }



    @Test
    void setPassword() {
        userNew.setPassword("test123");
        assertEquals(userNew.getPassword(), "test123");
    }


    @Test
    void setFirstname() {
        userNew.setFirstname("Petra");
        assertEquals(userNew.getFirstname(), "Petra");
    }


    @Test
    void setLastname() {
        userNew.setLastname("Ivić");
        assertEquals(userNew.getLastname(), "Ivić");
    }

}