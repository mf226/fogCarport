package FunctionLayer;

import com.itextpdf.forms.*;
import com.itextpdf.io.*;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.*;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.*;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.pdfa.*;
import com.itextpdf.test.*;
import com.itextpdf.layout.element.List;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author porse
 */
public class PDFGenerator {

    public static final String DEST = "C:\\Users\\porse\\CarportOrder.pdf";
    public static final String FOGLOGO = "src\\main\\webapp\\images\\fogIcon.png";

    public void PDFGenerator(Order order) throws java.io.IOException {
        PdfWriter writer = null;
        try {
            writer = new PdfWriter(DEST); //Later use ServletOutputStream to replace DEST
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            //Font
            PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
            //Adding logo
            Image logo = new Image(ImageDataFactory.create(FOGLOGO));
            document.add(logo);
            
            
            document.add(new Paragraph("Your order"));
            //add list
            List list = new List();
            list.setSymbolIndent(12);
            list.setListSymbol("\u2022");
            list.setFont(font);

            // Add ListItem objects
            list.add(new ListItem("Item 1"))
                    .add(new ListItem("Item 2"))
                    .add(new ListItem("Item 3"))
                    .add(new ListItem("Item 4"))
                    .add(new ListItem("Item 5"))
                    .add(new ListItem("Item 6"));
            //adding list
            document.add(list);
            document.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PDFGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (java.io.IOException ex) {
                Logger.getLogger(PDFGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
