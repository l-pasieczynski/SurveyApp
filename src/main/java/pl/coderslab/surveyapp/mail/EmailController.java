package pl.coderslab.surveyapp.mail;

import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pl.coderslab.surveyapp.user.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/app/admin")
public class EmailController {

    private final UserService userService;
    private final EmailSender emailSender;
    private final TemplateEngine templateEngine;

    public EmailController(UserService userService, EmailSender emailSender, TemplateEngine templateEngine) {
        this.userService = userService;
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    @GetMapping("/email")
    public ModelAndView sendEmail(@RequestParam(required = false) String emailAddress) {
        Email email = new Email();
        ArrayList<String> usersEmailList = new ArrayList<>();
        if (emailAddress != null){
            usersEmailList.add(emailAddress);
        }
        email.setUsersEmailAddress(usersEmailList);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("email", email);
        modelAndView.setViewName("admin/email");
        return modelAndView;
    }

    @PostMapping("/email")
    public ModelAndView sendEmail(@ModelAttribute("email") Email email) {

        Context context = new Context();
        context.setVariable("header", email.getHeader());
        context.setVariable("title", email.getTitle());
        context.setVariable("description", email.getDescription());
        String body = templateEngine.process("template", context);

        ModelAndView modelAndView = new ModelAndView();
        emailSender.sendEmail(email.getUsersEmailAddress(), email.getSubject(), body);
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }

    @ModelAttribute("usersEmails")
    public List<String> usersAddresses() {
        return userService.findAllUsersEmailAddresses();
    }

}
