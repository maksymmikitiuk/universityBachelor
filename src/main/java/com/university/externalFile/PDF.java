package com.university.externalFile;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.university.db.control.DBController;
import com.university.db.entity.DiplomasubjectsEntity;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by maksymmikitiuk on 6/7/17.
 */
public class PDF {
    public PDF() {
    }

    public void createPDFSubjects(TableView<DiplomasubjectsEntity> tableSubject) {
        Document doc = new Document();
        PdfWriter docWriter = null;

        try {
            //special font sizes
            BaseFont bf = BaseFont.createFont("/ui/config/f.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(bf, 12);
            Font fontB = new Font(bf, 12, Font.BOLD);

            //file path
            String path = getPath("Темы").getPath();
            docWriter = PdfWriter.getInstance(doc, new FileOutputStream(path));

            //document header attributes
            doc.addAuthor(DBController.currentUser.toString());
            doc.addCreationDate();
            doc.addProducer();
            doc.addCreator("ot.vntu.edu.ua");
            doc.addTitle("Теми");
            doc.setPageSize(PageSize.A4.rotate());

            //open document
            doc.open();

            //create a paragraph
            Paragraph paragraph = new Paragraph();

            Paragraph pTitle = new Paragraph();
            Phrase title = new Phrase("ТЕМИ\n\n\n", font);
            pTitle.setAlignment(Element.ALIGN_CENTER);
            pTitle.add(title);

            doc.add(pTitle);


            //specify column widths
            float[] columnWidths = {1f, 3f, 2.1f, 8f};
            //create PDF table with the given widths
            PdfPTable table = new PdfPTable(columnWidths);
            // set table width a percentage of the page width
            table.setWidthPercentage(90f);

            //insert column headings
            insertCell(table, "№\nп/п", Element.ALIGN_CENTER, 1, fontB);
            insertCell(table, "Прізвище, ім'я та\nпо - батькові", Element.ALIGN_CENTER, 1, fontB);
            insertCell(table, "Абревіатура\nгрупи\n ", Element.ALIGN_CENTER, 1, fontB);
            insertCell(table, "Тема", Element.ALIGN_CENTER, 1, fontB);
            table.setHeaderRows(1);

            int number = 1;
            for (DiplomasubjectsEntity diploma : tableSubject.getItems()) {
                String type;
                if (diploma.getType().getForm().getName().contains("робота"))
                    type = "Бакалаврська дипломна робота";
                else
                    type = "Бакалаврський дипломний проект";

                insertCell(table, String.valueOf(number) + ".", Element.ALIGN_CENTER, 1, fontB);
                insertCell(table, diploma.getStudent().getLastName() + "\n"
                        + diploma.getStudent().getFirstName() + "\n"
                        + diploma.getStudent().getMiddleName() + "\n    ", Element.ALIGN_LEFT, 1, font);
                insertCell(table, diploma.getStudent().getIdgroups().getAbbreviation(), Element.ALIGN_CENTER, 1, font);
                doubleFont(table, type, diploma.getSubject(), Element.ALIGN_JUSTIFIED, 1, fontB, font);
                number++;
            }


            //add the PDF table to the paragraph
            paragraph.add(table);
            // add the paragraph to the document
            doc.add(paragraph);

        } catch (DocumentException dex){
            dex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally        {
            if (doc != null) {
                //close the document
                doc.close();
            }
            if (docWriter != null) {
                //close the writer
                docWriter.close();
            }
        }
    }

    private void insertCell(PdfPTable table, String text, int align, int colspan, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setHorizontalAlignment(align);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setColspan(colspan);
        cell.setMinimumHeight(36f);
        table.addCell(cell);
    }

    private void doubleFont(PdfPTable table, String textBold, String textRegular, int align, int colspan, Font bold, Font font) {
        Phrase p = new Phrase();
        p.add(new Chunk(textBold + "\n", bold));
        p.add(new Chunk(textRegular, font));
        PdfPCell cell = new PdfPCell(p);
        cell.setHorizontalAlignment(align);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(colspan);
        cell.setMinimumHeight(36f);
        table.addCell(cell);
    }

    private File getPath(String name) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName(name + ".pdf");
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            return file;
        }
        return file;
    }
}

