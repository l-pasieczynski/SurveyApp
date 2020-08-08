package pl.coderslab.surveyapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("")
    public String home(){
        return "index.html";
    }
    @GetMapping("add")
        public String add(){
        return "addSurvey.html";
        }
}
