package pl.coderslab.surveyapp.mail;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.surveyapp.user.UserService;

import java.util.List;

@Controller
@RequestMapping("/app/admin")
public class EmailController {

    private final UserService userService;

    public EmailController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/email")
    public ModelAndView sendEmail() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("email", new Email());
        modelAndView.setViewName("application/email");
        return modelAndView;
    }

    @ModelAttribute("usersEmails")
    public List<String> usersAddresses() {
        return userService.findAllUsersEmailAddresses();
    }

}
