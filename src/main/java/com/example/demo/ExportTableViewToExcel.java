package com.example.demo;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExportTableViewToExcel {
    public static void exportData(TableView<History> tableView) throws IOException {
        // Create a new Excel file
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Get the data from the TableView
        ObservableList<History> data = tableView.getItems();

        // Create a new sheet
        XSSFSheet sheet = workbook.createSheet("MySheet");

        // Set the header row
        XSSFRow headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("operation");
        headerRow.createCell(1).setCellValue("description");


        // Add data to the sheet
        int rowIndex = 1;
        for (History item : data) {
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(item.getOperation());
            row.createCell(1).setCellValue(item.getDescription());
            rowIndex++;
        }

        // Write the Excel file to a file
        FileOutputStream fileOut = new FileOutputStream("myFile("+Loginpage.user.getUserName()+").xlsx");
        workbook.write(fileOut);
        fileOut.close();
    }
}
