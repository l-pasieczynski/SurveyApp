package pl.coderslab.surveyapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.surveyapp.mail.ContactMessage;
import pl.coderslab.surveyapp.mail.EmailSender;
import pl.coderslab.surveyapp.survey.SurveyFacade;
import pl.coderslab.surveyapp.user.User;
import pl.coderslab.surveyapp.user.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
class HomeController {

    private final UserService userService;
    private final SurveyFacade surveyFacade;
    private final EmailSender emailSender;

    public HomeController(UserService userService, SurveyFacade surveyFacade, EmailSender emailSender) {
        this.userService = userService;
        this.surveyFacade = surveyFacade;
        this.emailSender = emailSender;
    }

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("about")
    public String about() {
        return "about";
    }

    @GetMapping("contact")
    public ModelAndView contact() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", new ContactMessage());
        modelAndView.setViewName("contact");
        return modelAndView;
    }

    @PostMapping("contact")
    public String send(@ModelAttribute("message") ContactMessage contactMessage) {
        emailSender.sendContactForm(contactMessage.getName(), contactMessage.getEmail(), contactMessage.getMessage());
        return "index";
    }

    @GetMapping("freeSurveys")
    public String getAllFreeSurveys(Model model) {
        model.addAttribute("allSurveys", surveyFacade.findAllActiveFreeSurvey(true));
        return "surveys";
    }

    @GetMapping("freeSurveys/{id}")
    public String freeSurvey(@PathVariable("id") Long id, Model model) {
        model.addAttribute("surveys", surveyFacade.findFreeSurveyById(id));
        return "survey/survey" + id;
    }

    @GetMapping("register")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @PostMapping("register")
    public String createUserPost(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        userService.saveUser(user);
        System.out.println(user);
        return "login";
    }
}
