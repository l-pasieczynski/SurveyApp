package pl.coderslab.surveyapp.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
     private final UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @PutMapping("edit/{id}")
    public void editUser (@RequestBody User user, @PathVariable Long id) {
        userFacade.fillUserData(user, id);
    }

    @DeleteMapping("delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userFacade.deleteUser(id);
    }
}


