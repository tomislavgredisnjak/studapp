package hr.tvz.gredisnjak.studapp;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDTO> findAll();

    Optional<UserDTO> findById(Integer id);

    Optional<UserDTO> findByUsername(String username);
}
