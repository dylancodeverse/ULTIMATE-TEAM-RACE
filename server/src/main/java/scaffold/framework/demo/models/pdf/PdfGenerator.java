package scaffold.framework.demo.models.pdf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.source.ByteArrayOutputStream;

public class PdfGenerator {

    public static byte[] generatePdfFromHtml(String filePath, String[] placeholder, String[] replace) throws Exception {
        String htmlContent = modifyFile(filePath, placeholder, replace);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        HtmlConverter.convertToPdf(htmlContent, bos);
        byte[] pdfBytes = bos.toByteArray();
        return pdfBytes;
    }

    public static String modifyFile(String filePath, String[] placeholder, String[] replace) {
        StringBuilder modifiedContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
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