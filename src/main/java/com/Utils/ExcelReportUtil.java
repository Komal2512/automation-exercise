package com.Utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReportUtil {

    private static final String FILE_PATH = "target/TestResults.xlsx";
    private static final List<String[]> testData = new ArrayList<>();

    public static void writeTestResult(String testName, String status) {
        testData.add(new String[]{testName, status});
    }

    public static void saveReport() {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Test Results");

            // Header
            Row headerRow = sheet.createRow(0);
            CellStyle headerStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            headerStyle.setFont(font);

            Cell headerCell1 = headerRow.createCell(0);
            headerCell1.setCellValue("Test Name");
            headerCell1.setCellStyle(headerStyle);

            Cell headerCell2 = headerRow.createCell(1);
            headerCell2.setCellValue("Status");
            headerCell2.setCellStyle(headerStyle);

            // Data
            int rowNum = 1;
            for (String[] data : testData) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(data[0]);
                row.createCell(1).setCellValue(data[1]);
            }

            // Autosize columns
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);

            // Write to file
            FileOutputStream fileOut = new FileOutputStream(FILE_PATH);
            workbook.write(fileOut);
            fileOut.close();
            System.out.println("✅ Excel report saved to: " + FILE_PATH);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

