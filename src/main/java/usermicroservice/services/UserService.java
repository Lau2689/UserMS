package usermicroservice.services;


import usermicroservice.exceptions.BadArgumentsException;
import usermicroservice.exceptions.ResourceNotFoundException;
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
        List<User> allUsers = userRepository.findAll();
        if (allUsers.isEmpty()) {
            throw new ResourceNotFoundException("Not existing users");
        }else {
            return allUsers;
        }

    }

    public Optional<User> findUserById(String email) {
        Optional<User> userSearched = userRepository.findById(email);
        if(userSearched.isEmpty()){
            throw new ResourceNotFoundException("User not found");
        }else{
            return userSearched;
        }
    }

    public User createUser(User user) {
        Optional <User> newUSer = userRepository.findById(user.getEmail());
        if(newUSer.isPresent()){
            throw new BadArgumentsException("The user already exists");
        }
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        Optional <User> userToUpdate = userRepository.findById(user.getEmail());
        if(userToUpdate.isPresent()){
            return userRepository.save(user);
        }
        throw new ResourceNotFoundException("User not found");
    }

    public void deleteUser(String email) {
        userRepository.deleteById(email);
    }

    public List<User> findUserByName(String name) {
        List<User> userSearchedByName = userRepository.findByName(name);
        if(userSearchedByName.isEmpty()){
            throw new ResourceNotFoundException("No users with name" + name);
        }else{
            return userSearchedByName;
        }
    }

}