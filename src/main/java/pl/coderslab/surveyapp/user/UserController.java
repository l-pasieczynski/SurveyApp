package pl.coderslab.surveyapp.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("edit/{id}")
    public void editUser (@RequestBody User user, @PathVariable Long id) {
        userService.fillUserData(user, id);
    }

    @DeleteMapping("delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("")
    public String userHome(){
        return "application/user/user";
    }
}


