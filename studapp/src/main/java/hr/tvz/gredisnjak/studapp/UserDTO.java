package hr.tvz.gredisnjak.studapp;

import java.math.BigInteger;
import java.util.Set;

public class UserDTO {

    private BigInteger id;
    private String username;
    private String firstName;
    private String lastName;
    private Set<String> authorities;

    public UserDTO(BigInteger id, String username, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserDTO() {
    }

    public BigInteger getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString(){
        return "UserDTO{" +
                "Name=' " + firstName + '\'' +
                ", surname=" + lastName +
                '}';
    }
}
