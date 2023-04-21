package usermicroservice.services;

import usermicroservice.models.User;
import usermicroservice.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUserById(String email) {
        return userRepository.findById(email);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(String email) {
        userRepository.deleteById(email);
    }

    public List<User> findUserByName(String name) {
        return userRepository.findByName(name);
    }

}