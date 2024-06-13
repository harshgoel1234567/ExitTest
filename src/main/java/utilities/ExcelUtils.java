package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {

    public static List<String[]> readExcelData(String filePath, String sheetName) throws IOException {
        List<String[]> data = new ArrayList<>();
        try (FileInputStream file = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(file)) {
            Sheet sheet = workbook.getSheet(sheetName);
            for (Row row : sheet) {
                String[] rowData = new String[row.getLastCellNum()];
                for (int cn = 0; cn < row.getLastCellNum(); cn++) {
                    Cell cell = row.getCell(cn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    rowData[cn] = getCellValueAsString(cell);
                }
                data.add(rowData);
            }
        }
        return data;
    }

    private static String getCellValueAsString(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "";
        }
    }

    public static boolean isExecutionRequired(String filePath, String sheetName, String testCaseName) throws IOException {
        List<String[]> data = readExcelData(filePath, sheetName);
        for (String[] row : data) {
            if (row.length > 1 && row[0].equalsIgnoreCase(testCaseName) && row[1].equalsIgnoreCase("Yes")) {
                return true;
            }
        }
        return false;
    }

    public static String getTestData(String filePath, String sheetName, String testCaseName, String columnName) throws IOException {
        try (FileInputStream file = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(file)) {
            Sheet sheet = workbook.getSheet(sheetName);

            int testCaseColumn = -1;
            int columnIndex = -1;
            int executionRequiredColumn = -1;

            // Get the header row
            Row headerRow = sheet.getRow(0);
            for (Cell cell : headerRow) {
                if (cell.getStringCellValue().equalsIgnoreCase("TestCaseName")) {
                    testCaseColumn = cell.getColumnIndex();
                }
                if (cell.getStringCellValue().equalsIgnoreCase(columnName)) {
                    columnIndex = cell.getColumnIndex();
                }
                if (cell.getStringCellValue().equalsIgnoreCase("Execution Required")) {
                    executionRequiredColumn = cell.getColumnIndex();
                }
            }

            // Print header row information for debugging
            System.out.println("TestCase Column Index: " + testCaseColumn);
            System.out.println("Column Index: " + columnIndex);
            System.out.println("Execution Required Column Index: " + executionRequiredColumn);

            // Iterate through the rows to find the test case with Execution Required as Yes
            for (Row row : sheet) {
                Cell testCaseCell = row.getCell(testCaseColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                Cell executionRequiredCell = row.getCell(executionRequiredColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                if (testCaseCell != null && executionRequiredCell != null
                    && testCaseCell.getStringCellValue().equalsIgnoreCase(testCaseName)
                    && executionRequiredCell.getStringCellValue().equalsIgnoreCase("Yes")) {
                    Cell dataCell = row.getCell(columnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    if (dataCell != null) {
                        System.out.println("Found data for TestCaseName: " + testCaseName + ", Execution Required: Yes, " + columnName + ": " + getCellValueAsString(dataCell));
                        return getCellValueAsString(dataCell);
                    }
                }
            }
        }
        return null;
    }

    public static List<Map<String, String>> getTestCases(String filePath, String sheetName) throws IOException {
        List<Map<String, String>> testCases = new ArrayList<>();
        try (FileInputStream file = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(file)) {
            Sheet sheet = workbook.getSheet(sheetName);
            Row headerRow = sheet.getRow(0);
            List<String> headers = new ArrayList<>();
            for (Cell cell : headerRow) {
                headers.add(cell.getStringCellValue());
            }

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row
                Map<String, String> testCase = new HashMap<>();
                for (int cn = 0; cn < headers.size(); cn++) {
                    Cell cell = row.getCell(cn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    testCase.put(headers.get(cn), getCellValueAsString(cell));
                }
                testCases.add(testCase);
            }
        }
        return testCases;
    }
}
