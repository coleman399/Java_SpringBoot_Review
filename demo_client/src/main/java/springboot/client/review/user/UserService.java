package springboot.client.review.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    private UserClient userClient;

    @Autowired
    public UserService(UserClient userClient) {
        this.userClient = userClient;
    }

    public List<User> getUsers() {
        return userClient.getUsers();
    }

    public User getUser(Long id) {
        return userClient.getUser(id);
    }

    public User createUser(User User) {
        return userClient.createUser(User);
    }

    public User updateUser(User User, Long id) {
        return userClient.updateUser(User, id);
    }

    public void deleteUser(Long id) {
        userClient.deleteUser(id);
    }
}
