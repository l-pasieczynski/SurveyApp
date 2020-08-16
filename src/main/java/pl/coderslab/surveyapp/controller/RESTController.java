package pl.coderslab.surveyapp.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pl.coderslab.surveyapp.mail.ContactMessage;
import pl.coderslab.surveyapp.mail.Email;
import pl.coderslab.surveyapp.mail.EmailSender;
import pl.coderslab.surveyapp.survey.FreeSurvey;
import pl.coderslab.surveyapp.survey.SurveyFacade;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class RESTController {

    private final SurveyFacade surveyFacade;
    private final EmailSender emailSender;
    private final TemplateEngine templateEngine;

    public RESTController(SurveyFacade surveyFacade, EmailSender emailSender, TemplateEngine templateEngine) {
        this.surveyFacade = surveyFacade;
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
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
    public ModelAndView sendContactMessage(@RequestBody @Valid ContactMessage contactMessage, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("contact");
            return modelAndView;
        }
        emailSender.sendContactForm(contactMessage.getName(), contactMessage.getEmail(), contactMessage.getMessage());
        modelAndView.setViewName("contact");
        return modelAndView;
    }

    @PostMapping("/app/admin/email")
    public ModelAndView sendEmail(@RequestBody Email email) {

        Context context = new Context();
        context.setVariable("header", email.getHeader());
        context.setVariable("title", email.getTitle());
        context.setVariable("description", email.getDescription());
        String body = templateEngine.process("template", context);

        emailSender.sendEmail(email.getUsersEmailAddress(), email.getSubject(), body);
        ModelAndView modelAndView = new ModelAndView();
        //TODO sprawdzić poniższy adres
        modelAndView.setViewName("application/admin");
        return modelAndView;
    }

}
