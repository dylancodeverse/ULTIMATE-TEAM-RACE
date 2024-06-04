package scaffold.framework.demo.models.pdf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.source.ByteArrayOutputStream;

public class PdfGenerator {


    public static void generatePdfFromHtml(String htmlContent) throws Exception {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            HtmlConverter.convertToPdf(htmlContent, bos);
            byte[] pdfBytes = bos.toByteArray();
            Files.write(Paths.get("congrats.pdf"), pdfBytes);
            System.out.println("Le PDF en mode paysage a été généré avec succès à partir du fichier HTML : ");
        } catch (IOException e) {
            System.err.println("Erreur lors de la génération du PDF à partir du fichier HTML :  + - " + e.getMessage());
            throw e;
        }
    }

    public static String modifyFile(String filePath, String[] placeholder, String [] replace) {
        StringBuilder modifiedContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (int i = 0 ; i< placeholder.length ; i++) {
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