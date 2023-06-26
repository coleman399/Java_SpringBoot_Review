package springboot.review.user;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    public User createUser(@Valid User user) {
        return userRepository.save(user);
    }

    public User updateUser(@Valid User user, Long id) {
        userRepository.findById(id).orElseThrow(() -> new NullPointerException());
        User userInformation = userRepository.findById(id).get();
        userInformation.setFirstName(user.getFirstName());
        userInformation.setLastName(user.getLastName());
        userInformation.setUsername(user.getUsername());
        userInformation.setPassword(user.getUsername());
        return userRepository.save(userInformation);
    }

    public void deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(() -> new NullPointerException());
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
    }
    


}
