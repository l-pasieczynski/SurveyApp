package pl.coderslab.surveyapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.surveyapp.survey.SurveyFacade;
import pl.coderslab.surveyapp.user.User;
import pl.coderslab.surveyapp.user.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
class HomeController {

    private final UserService userService;
    private final SurveyFacade surveyFacade;

    public HomeController(UserService userService, SurveyFacade surveyFacade) {
        this.userService = userService;
        this.surveyFacade = surveyFacade;
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
    public String contact() {
        return "contact";
    }

    @GetMapping("surveys")
    public String getAllFreeSurveys(Model model) {
        model.addAttribute("allSurveys", surveyFacade.findAllFreeSurveys());
        return "surveys";
    }

    @GetMapping("surveys/{id}")
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
        return "login";
    }
}
