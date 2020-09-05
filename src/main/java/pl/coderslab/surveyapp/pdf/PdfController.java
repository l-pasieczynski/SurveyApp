package pl.coderslab.surveyapp.pdf;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.surveyapp.answer.Answer;
import pl.coderslab.surveyapp.survey.SurveyFacade;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/app/admin")
public class PdfController {

    private final SurveyFacade surveyFacade;
    private final GeneratePdfReport generatePdfReport;

    public PdfController(SurveyFacade surveyFacade, GeneratePdfReport generatePdfReport) {
        this.surveyFacade = surveyFacade;
        this.generatePdfReport = generatePdfReport;
    }

    @GetMapping(value = "/results/survey/pdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public String createPdf(@PathVariable Long id, Model model) throws Exception {

        List<Answer> results = surveyFacade.findAllAnswerBySurveyId(id);

        Map<String,String> data = new HashMap<String,String>();
        data.put("name","James");
        generatePdfReport.createPdf("pdfTemplate",data);

        return "redirect:../results";

//                ByteArrayInputStream bis = GeneratePdfReport.resultsReport(results);

//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "inline; filename=resultsReport.pdf");
//
//        return ResponseEntity
//                .ok()
//                .headers(headers)
//                .contentType(MediaType.APPLICATION_PDF)
//                .body(new InputStreamResource(bis));
    }

}

