package pl.coderslab.surveyapp.pdf;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

@Component
public class GeneratePdfReport {

    private TemplateEngine templateEngine;

    public GeneratePdfReport(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public void createPdf(String templateName, Map map) throws Exception {
        Assert.notNull(templateName, "The templateName can not be null");

        Context context = new Context();
        if (map != null) {
            Iterator itMap = map.entrySet().iterator();
            while (itMap.hasNext()) {
                Map.Entry pair = (Map.Entry) itMap.next();
                context.setVariable(pair.getKey().toString(), pair.getValue());
            }
        }

        String processedHtml = templateEngine.process(templateName, context);
        FileOutputStream outputStream = null;
//        String fileName = UUID.randomUUID().toString();
        String fileName = "survey_pdf";
        try {
            final File outputFile = File.createTempFile(fileName, ".pdf");
            outputStream = new FileOutputStream(outputFile);

            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(processedHtml);
            renderer.layout();
            renderer.createPDF(outputStream, false);
            renderer.finishPDF();
            System.out.println("PDF created successfully");
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //    public static ByteArrayInputStream resultsReport(List<Answer> results) {
//
//        Document document = new Document();
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//
//        try {
//
//            PdfPTable table = new PdfPTable(3);
//            table.setWidthPercentage(60);
//            table.setWidths(new int[]{1, 3, 3});
//
//            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
//
//            PdfPCell hcell;
//            hcell = new PdfPCell(new Phrase("Id", headFont));
//            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(hcell);
//
//            hcell = new PdfPCell(new Phrase("Name", headFont));
//            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(hcell);
//
//            hcell = new PdfPCell(new Phrase("Population", headFont));
//            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(hcell);
//
//            for (Answer answer : results) {
//                PdfPCell cell;
//
//                cell = new PdfPCell(new Phrase(answer.getId().toString()));
//                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                table.addCell(cell);
//
//                cell = new PdfPCell(new Phrase(String.valueOf(answer.getQuestion())));
//                cell.setPaddingLeft(5);
//                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//                table.addCell(cell);
//
//                cell = new PdfPCell(new Phrase(String.valueOf(answer.getAnswer())));
//                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                cell.setPaddingRight(5);
//                table.addCell(cell);
//            }
//
//            PdfWriter.getInstance(document, out);
//            document.open();
//            document.add(table);
//
//            document.close();
//
//        } catch (DocumentException ex) {
//            System.out.println("Error occured" + ex);
//        }
//
//        return new ByteArrayInputStream(out.toByteArray());
//
//    }

}
