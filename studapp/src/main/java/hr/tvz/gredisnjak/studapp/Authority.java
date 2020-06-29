package hr.tvz.gredisnjak.studapp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name="AUTHORITY")
public class Authority implements Serializable {

    private static final long serialVersionUID= -8633422296916401216L;

    @Id
    @Column(name="id", columnDefinition = "BIGINT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    @Column(name="name")
    private String name;

    @ManyToMany(targetEntity = User.class, mappedBy = "authorities")
    private List<User> users;

    public Authority() {
    }

    public Authority(BigInteger id, String name) {
        this.id = id;
        this.name = name;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }
}
