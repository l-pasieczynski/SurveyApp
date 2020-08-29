package pl.coderslab.surveyapp.survey;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.surveyapp.answer.Answer;
import pl.coderslab.surveyapp.answer.AnswerService;
import pl.coderslab.surveyapp.question.Question;
import pl.coderslab.surveyapp.question.QuestionService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("surveyTitle")
@RequestMapping("/app/survey/create")
public class SurveyCreatingController {

   private final QuestionService questionService;
   private final AnswerService answerService;
   private final SurveyService surveyService;

    public SurveyCreatingController(QuestionService questionService, AnswerService answerService, SurveyService surveyService) {
        this.questionService = questionService;
        this.answerService = answerService;
        this.surveyService = surveyService;
    }

    @GetMapping("")
    public String createSurvey(){
        return ("surveyCreating/surveyCreate");
    }

    @PostMapping("")
    public String createSurveyFirsStep(Model m, @RequestParam(required = false) String title, String questionType,
                                       String question, String quantityAnswer, HttpSession session){
        List<Integer> quantity = new ArrayList<>();
        for(int i=0; i<Integer.parseInt(quantityAnswer);i++){
            quantity.add(i+1);
        }
        System.out.println(title+" "+questionType+" "+question+" "+quantityAnswer);
        m.addAttribute("howMuch",quantity);
        m.addAttribute("surveyTitle",title);
        m.addAttribute("question",question);

        Survey survey = new Survey();
        List <Question> questions = new ArrayList<>();
        Question q = new Question();
        q.setQuestion(question);
        q.setQuestionType(questionType);
        questionService.saveQuestion(q);
        if(title!=null){
            questions.add(q);
            survey.setName(title);
            survey.setQuestions(questions);
            surveyService.save(survey);
        }else {

           Long id = surveyService.findBySurveyName((String) session.getAttribute("surveyTitle")).getId();
            questions=surveyService.findById(id).getQuestions();
           surveyService.save(surveyService.findById(id).setQuestions(questions));
        }
        return ("surveyCreating/surveyCreateSecondStep");


    }
    @PostMapping("/answer")
    public String createSurveySecondStep(Model m,@RequestParam List<String> answers, String action,String question,String surveyName){
        System.out.println(action);
        System.out.println(answers);
        System.out.println(question);
        List<Answer>  answerList = new ArrayList<>();

        for(int i=0 ; i<answers.size();i++){
            answerList.add(new Answer().setAnswer(answers.get(i)));
        }
        questionService.saveQuestion(questionService.findByQuestion(question).setAnswer(answerList));

        if(action.equals("next")){
            return ("surveyCreating/surveyCreateNextQuestion");
        }else{
            return ("surveyCreating/surveyCreate");
        }


    }




}

