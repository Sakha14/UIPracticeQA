package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import schema.dataHolder;

public class ExcelUtilities {

    public List<dataHolder> readExcelData(String excelFilePath) {
        List<dataHolder> detailsList = new ArrayList<>();
        DecimalFormat integerFormat = new DecimalFormat("#");

        try (FileInputStream inputstream = new FileInputStream(excelFilePath);
             XSSFWorkbook workbook = new XSSFWorkbook(inputstream)) {

            XSSFSheet sheet = workbook.getSheet("sheet1");
            if (sheet == null) {
                System.out.println("Sheet not found!");
                return detailsList;
            }

            // Start from the second row (index 1) to skip the header
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                XSSFRow row = sheet.getRow(rowIndex);
                if (row == null) continue; // Skip empty rows

                dataHolder details = new dataHolder();
                details.setFirstname(getCellValue(row.getCell(0), integerFormat)); // firstName
                details.setLastname(getCellValue(row.getCell(1), integerFormat));  // lastName
                details.setEmail(getCellValue(row.getCell(2), integerFormat));     // email
                details.setPhonenumber(getCellValue(row.getCell(3), integerFormat));     // phone
                details.setSubject(getCellValue(row.getCell(4), integerFormat));
                details.setAddress(getCellValue(row.getCell(5), integerFormat));
                details.setState(getCellValue(row.getCell(6), integerFormat));
                details.setCity(getCellValue(row.getCell(7), integerFormat));

                // Debug prints to check values
                System.out.println("Read from Excel:");
                System.out.println("First Name: " + details.getFirstname());
                System.out.println("Last Name: " + details.getLastname());
                System.out.println("Email: " + details.getEmail());
                System.out.println("Phone: " + details.getPhonenumber());
                System.out.println("Subject: " +details.getSubject());
                System.out.println("Address: " +details.getAddress());
                System.out.println("State: " +details.getState());
                System.out.println("City: " +details.getCity());

                detailsList.add(details);
            }

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }

        return detailsList;
    }

    private String getCellValue(XSSFCell cell, DecimalFormat integerFormat) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return isWholeNumber(cell) ? integerFormat.format(cell.getNumericCellValue()) : String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "UNKNOWN TYPE";
        }
    }

    private boolean isWholeNumber(XSSFCell cell) {
        double cellValue = cell.getNumericCellValue();
        return cellValue == Math.floor(cellValue);
    }
}
