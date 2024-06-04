package scaffold.framework.demo.models.pdf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.io.source.ByteArrayOutputStream;


public class PdfGenerator {

    public static byte[] generatePdfFromHtml(String filePath, String[] placeholder, String[] replace) throws Exception {
        String htmlContent = modifyFile(filePath, placeholder, replace);
        System.out.println("HTML content after modification: " + htmlContent);
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ConverterProperties converterProperties = new ConverterProperties();
        PageSize pageSize = PageSize.A4.rotate(); // Définir la page au format A4 paysage
        
        PdfWriter writer = new PdfWriter(bos);
        PdfDocument pdfDoc = new PdfDocument(writer);
        pdfDoc.setDefaultPageSize(pageSize);
        
        Document document = new Document(pdfDoc);
        HtmlConverter.convertToPdf(htmlContent, pdfDoc, converterProperties);
        document.close();
        
        byte[] pdfBytes = bos.toByteArray();
        return pdfBytes;
    }
    
    public static String modifyFile(String filePath, String[] placeholder, String[] replace) {
        StringBuilder modifiedContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath, Charset.forName("UTF-8")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (int i = 0; i < placeholder.length; i++) {
                    line = line.replace(placeholder[i], replace[i]);
                }
                modifiedContent.append(line);
                modifiedContent.append(System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la modification du fichier : " + filePath + " - " + e.getMessage());
        }
        return modifiedContent.toString();
    }


}
