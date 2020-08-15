package pl.coderslab.surveyapp.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.coderslab.surveyapp.user.CurrentUser;

@Controller
@SessionAttributes({"userId", "username"})
@RequestMapping("/application")
public class AppController {

    @GetMapping("/app-home")
    public String appStart(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        if (currentUser == null) {
            return "/login";
        } else {
            Long id = currentUser.getUser().getId();
            model.addAttribute("userId", id);
            model.addAttribute("username", currentUser.getUsername());
            return "application/app-home";
        }
    }
}
