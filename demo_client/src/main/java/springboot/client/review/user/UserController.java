package springboot.client.review.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private UserService UserService;

    @Autowired
    public UserController(UserService UserService) {
        this.UserService = UserService;
    }

    @GetMapping({ "", "index", "/" })
    public String goToIndex(Model model) {
        List<User> users = UserService.getUsers();
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("getUser/{id}")
    public String goToUserPage(@PathVariable("id") Long id, Model model) {
        User user = UserService.getUser(id);
        model.addAttribute("user", user);
        return "display-User";
    }

    @GetMapping("/createUser")
    public String goToCreateUserPage(Model model) {
        model.addAttribute("user", new User());
        return "create-user";
    }

    @PostMapping("/createUser")
    public String createUser(User User) {
        UserService.createUser(User);
        return "redirect:/";
    }

    @GetMapping("/updateUser/{id}")
    public String goToUpdateUserPage(@PathVariable("id") Long id, Model model) {
        User foundUser = UserService.getUser(id);
        model.addAttribute("user", foundUser);
        return "update-user";
    }

    @PostMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") Long id, User user) {
        UserService.updateUser(user, id);
        return "redirect:/";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        UserService.deleteUser(id);
        return "redirect:/";
    }
}
