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
import schema.DataHolder;

public class ExcelUtilities {

    public List<DataHolder> readExcelData(String excelFilePath) {
        List<DataHolder> detailsList = new ArrayList<>();
        DecimalFormat integerFormat = new DecimalFormat("#");

        try (FileInputStream inputstream = new FileInputStream(excelFilePath);
             XSSFWorkbook workbook = new XSSFWorkbook(inputstream)) {

            XSSFSheet sheet = workbook.getSheet("Sheet1");
            if (sheet == null) {
                System.out.println("Sheet not found!");
                return detailsList;
            }

            // Start from the second row (index 1) to skip the header
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                XSSFRow row = sheet.getRow(rowIndex);
                if (row == null) continue; // Skip empty rows

                DataHolder details = new DataHolder();
                details.setFirstName(getCellValue(row.getCell(0), integerFormat)); // firstName
                details.setLastName(getCellValue(row.getCell(1), integerFormat));  // lastName
                details.setEmail(getCellValue(row.getCell(2), integerFormat));     // email
                details.setPhone(getCellValue(row.getCell(3), integerFormat));     // phone
                details.setBuildingName(getCellValue(row.getCell(4), integerFormat));
                details.setBuildingNumber(getCellValue(row.getCell(5), integerFormat));
                details.setSubBuildingName(getCellValue(row.getCell(6), integerFormat));
                details.setAddressline1(getCellValue(row.getCell(7), integerFormat));
                details.setAddressline2(getCellValue(row.getCell(8), integerFormat));
                details.setAddressline3(getCellValue(row.getCell(9), integerFormat));
                details.setCity(getCellValue(row.getCell(10), integerFormat));
                details.setPostalcode(getCellValue(row.getCell(11), integerFormat));
                details.setSearch(getCellValue(row.getCell(12), integerFormat));
                
                // Debug prints to check values
                System.out.println("Read from Excel:");
                System.out.println("First Name: " + details.getFirstName());
                System.out.println("Last Name: " + details.getLastName());
                System.out.println("Email: " + details.getEmail());
                System.out.println("Phone: " + details.getPhone());
                System.out.println("Building Name: " +details.getBuildingName());
                System.out.println("Building Number: " +details.getBuildingNumber());
                System.out.println("Sub Building Name: " +details.getSubBuildingName());
                System.out.println("AddressLine1: " +details.getAddressline1());
                System.out.println("AddressLine2: " +details.getAddressline2());
                System.out.println("AddressLine3: " +details.getAddressline3());
                System.out.println("City: " +details.getCity());
                System.out.println("Postal Code: " +details.getPostalcode());
                System.out.println("Search City or Postal Code: " +details.getSearch());
                
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
