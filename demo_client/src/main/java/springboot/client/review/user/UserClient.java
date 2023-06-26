package springboot.client.review.user;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("user-service")
public interface UserClient {
    
    @GetMapping("/api/v1/users")
    List<User> getUsers();

    @GetMapping("/api/v1/users/{id}")
    User getUser(@PathVariable("id") Long id);

    @PostMapping("/api/v1/users")
    User createUser(@RequestBody User user);

    @PutMapping("/api/v1/users/{id}")
    User updateUser(@RequestBody User user, @PathVariable("id") Long id);

    @DeleteMapping("/api/v1/users/{id}")
    void deleteUser(@PathVariable("id") Long id);
}
