package pl.coderslab.surveyapp.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @PutMapping("edit/{id}")
//    public void editUser (@RequestBody User user, @PathVariable Long id) {
//        userService.fillUserData(user, id);
//    }
//
//    @DeleteMapping("delete/{id}")
//    public void deleteUser(@PathVariable Long id) {
//        userService.delete(id);
//    }

    @GetMapping("")
    public String userHome(){
        return "application/user/user";
    }

    @GetMapping("/edit")
    public String editUser(Model m){
        Authentication a =SecurityContextHolder.getContext().getAuthentication();
        String username= a.getName();
        m.addAttribute("user",userService.findByUsername(username));

        return "application/user/user-edit";
    }
    @PostMapping("/edit")
    public String editUserData(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "application/user/user-edit";
        }
        userService.updateUser(user);

        return "application/user/user";
    }
}


