package usermicroservice.services;

import usermicroservice.models.User;
import usermicroservice.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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

    public Optional<User> findUserById (String email){
        return  userRepository.findById(email);
    }

    public User createUser (User user){
        return userRepository.save(user);
    }

    public Optional <User> updateUser (User user, String email){
        return userRepository.findById(email)
            .map(newUser ->{
                newUser.setEmail(user.getEmail());
                newUser.setName(user.getName());
                newUser.setLastName(user.getLastName());
                newUser.setCity(user.getCity());
                newUser.setPaymentMethod(user.getPaymentMethod());
                newUser.setFidelityPoints(user.getFidelityPoints());
                newUser.setPurchasePriceAverage(user.getPurchasePriceAverage());

                return userRepository.save(newUser);

            });

    }

    public void deleteUser (String email){
        userRepository.deleteById(email);
    }
}