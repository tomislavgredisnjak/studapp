package hr.tvz.gredisnjak.studapp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<User, Integer> {

    List<User> findAll();

    Optional<User> findById(Integer id);

    Optional<User> findOneByUsername(String username);
}
