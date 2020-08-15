package pl.coderslab.surveyapp.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.surveyapp.mail.ContactMessage;
import pl.coderslab.surveyapp.mail.EmailSender;
import pl.coderslab.surveyapp.survey.FreeSurvey;
import pl.coderslab.surveyapp.survey.SurveyFacade;

@RestController
@RequestMapping("/")
public class RESTController {

    private final SurveyFacade surveyFacade;
    private final EmailSender emailSender;

    public RESTController(SurveyFacade surveyFacade, EmailSender emailSender) {
        this.surveyFacade = surveyFacade;
        this.emailSender = emailSender;
    }

    @PostMapping("surveys/{id}")
    public ModelAndView addFreeSurvey(@RequestBody FreeSurvey freeSurvey) {
        surveyFacade.saveFreeSurvey(freeSurvey);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("surveys");
        return modelAndView;
    }

    @DeleteMapping("admin/surveys/delete/{id}")
    public ModelAndView deleteFreeSurvey(@PathVariable Long id) {
        surveyFacade.deleteFreeSurvey(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("surveys");
        return modelAndView;
    }

    @PutMapping("admin/surveys/edit/{id}")
    public ModelAndView editFreeSurvey(@RequestBody FreeSurvey freeSurvey, Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("surveys");
        return modelAndView;
    }

    @PostMapping("contact")
    public ModelAndView sendContactMessage(@RequestBody ContactMessage contactMessage) {
        emailSender.sendContactForm(contactMessage.getName(), contactMessage.getEmail(), contactMessage.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("contact");
        return modelAndView;
    }


}
