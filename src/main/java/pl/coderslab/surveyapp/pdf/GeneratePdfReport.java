package pl.coderslab.surveyapp.pdf;

import com.itextpdf.text.Document;
import pl.coderslab.surveyapp.answer.Answer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class GeneratePdfReport {

    public static ByteArrayInputStream resultsReport(List<Answer> results) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        return null;

    }
}
