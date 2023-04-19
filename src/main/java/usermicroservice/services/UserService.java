package usermicroservice.services;

import usermicroservice.models.User;
import usermicroservice.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById (String email){
        return  userRepository.findById(email).get();
    }

    public User createUser (User user){
        return userRepository.save(user);
    }

    public User updateUser (User user, String email){
        User userToUpdate =  userRepository.findById(email).get();
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setName(user.getName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setCity(user.getCity());
        userToUpdate.setPaymentMethod(user.getPaymentMethod());
        userToUpdate.setFidelityPoints(user.getFidelityPoints());
        userToUpdate.setPurchasePriceAverage(user.getPurchasePriceAverage());

        return userRepository.save(user);
    }

    public User deleteUser (String email){
        User userToDelete = userRepository.findById(email).get();
        userRepository.deleteById(email);

        return  userToDelete;
    }
}