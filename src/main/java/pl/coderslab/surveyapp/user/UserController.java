package pl.coderslab.surveyapp.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/app/user")
public class UserController {
    private final UserService userService;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserController(UserService userService,  BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
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
    public String userHome(Model m, HttpSession session){
        m.addAttribute("user",userService.findById((Long) session.getAttribute("userId")));
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
    public String editUserData(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                               HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "application/user/user-edit";
        }
        user.setId((Long) session.getAttribute("userId"));
        user.setPassword(userService.findById((Long) session.getAttribute("userId")).getPassword());
        userService.updateUser(user);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.setAuthenticated(false);


        return "login";
    }
    @GetMapping("/edit/password")
    public String editUserPassword(Model m){


        return "application/user/user-edit-password";
    }
    @PostMapping("/edit/password")
    public String editUserPasswordPost(HttpSession session ,@Valid @RequestParam String oldPassword, String newPassword,
                                       Model m ){
        User u = userService.findById((Long) session.getAttribute("userId"));
        if(!BCrypt.checkpw(oldPassword,u.getPassword())){

            m.addAttribute("pswErr","WRONG OLD PASSWORD");
            return "application/user/user-edit-password";
        }

        u.setPassword(passwordEncoder.encode(newPassword));
        userService.updateUser(u);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.setAuthenticated(false);


        return "login";

    }
    @GetMapping("/delete")
    public String deleteUser(HttpSession session){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.setAuthenticated(false);

        userService.delete((Long)session.getAttribute("userId"));


        return "redirect:/register";
    }

    @GetMapping("/calendar")
    public String calendar(){
        return "application/calendar";
    }

    @GetMapping("/team")
    public String team(){
        return "application/team";
    }

}


