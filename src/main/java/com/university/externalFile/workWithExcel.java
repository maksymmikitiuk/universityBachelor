package com.university.externalFile;

import com.university.db.control.groupController;
import com.university.db.entity.StudentsEntity;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class workWithExcel {
    private String pathToFile;
    private FileInputStream inputStream;
    private Workbook workbook;
    private List<StudentsEntity> columnList = new ArrayList<StudentsEntity>();

    public workWithExcel(String pathToFile) {
        this.pathToFile = pathToFile;

        if (openFile()) {
            readFile();
            closeFile();
        }
    }

    private boolean openFile() {
        try {
            inputStream = new FileInputStream(new File(pathToFile));
            workbook = new XSSFWorkbook(inputStream);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private void readFile() {
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();

        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            StudentsEntity student = new StudentsEntity();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                switch (cell.getColumnIndex()) {
                    case 0:
                        student.setLastName(cell.getStringCellValue());
                        break;
                    case 1:
                        student.setFirstName(cell.getStringCellValue());
                        break;
                    case 2:
                        student.setMiddleName(cell.getStringCellValue());
                        break;
                    case 3:
                        student.setStudentidcard(cell.getStringCellValue());
                        break;
                    case 4:
                        Double d = cell.getNumericCellValue();
                        student.setIdgroups(new groupController().getGroupById(d.intValue()));
                        break;
                    case 5:
                        student.setPhone(cell.getStringCellValue());
                        break;
                    case 6:
                        student.setEmail(cell.getStringCellValue());
                        break;
                }

            }

            columnList.add(student);
        }
    }

    private void closeFile() {
        try {
            workbook.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<StudentsEntity> getColumnList() {
        return columnList;
    }
}
