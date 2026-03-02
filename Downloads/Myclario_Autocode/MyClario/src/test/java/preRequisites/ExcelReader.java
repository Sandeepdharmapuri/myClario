package preRequisites;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ExcelReader {

    private String filePath;
    private Workbook workbook;
    private Properties pr;

    public ExcelReader(String fileKey) {
        loadProperties();
        this.filePath = pr.getProperty(fileKey);

        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            workbook = new XSSFWorkbook(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadProperties() {
        pr = new Properties();
        try (FileInputStream fis = new FileInputStream("src/test/resources/configfiles/config.properties")) {
            pr.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getDataBySheetName(String sheetName, int rowNum, int colNum) {
        Sheet sheet = workbook.getSheet(sheetName);
        return getCellData(sheet, rowNum, colNum);
    }

    public String getDataBySheetIndex(int sheetIndex, int rowNum, int colNum) {
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        return getCellData(sheet, rowNum, colNum);
    }

    public List<Map<String, String>> getSheetDataAsMap(String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        List<Map<String, String>> dataList = new ArrayList<>();

        if (sheet == null) {
            System.out.println("Sheet not found: " + sheetName);
            return dataList;
        }

        Row headerRow = sheet.getRow(0);
        if (headerRow == null) {
            System.out.println("Header row is missing in sheet: " + sheetName);
            return dataList;
        }

        int columnCount = headerRow.getLastCellNum();

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            Map<String, String> dataMap = new HashMap<>();
            for (int j = 0; j < columnCount; j++) {
                Cell headerCell = headerRow.getCell(j, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                if (headerCell == null) continue;

                String header = headerCell.getStringCellValue().trim();
                String cellValue = getCellData(row, j);
                dataMap.put(header, cellValue);
            }
            dataList.add(dataMap);
        }
        return dataList;
    }

    public List<Map<String, String>> getSheetDataAsMapByIndex(int sheetIndex) {
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        List<Map<String, String>> dataList = new ArrayList<>();

        if (sheet == null) {
            System.out.println("Sheet index " + sheetIndex + " not found.");
            return dataList;
        }

        Row headerRow = sheet.getRow(0);
        if (headerRow == null) {
            System.out.println("Header row is missing in sheet index: " + sheetIndex);
            return dataList;
        }

        int columnCount = headerRow.getLastCellNum();

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            Map<String, String> dataMap = new HashMap<>();
            for (int j = 0; j < columnCount; j++) {
                Cell headerCell = headerRow.getCell(j, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                if (headerCell == null) continue;

                String header = headerCell.getStringCellValue().trim();
                String cellValue = getCellData(row, j);
                dataMap.put(header, cellValue);
            }
            dataList.add(dataMap);
        }
        return dataList;
    }

    public List<String> getSheetNames() {
        List<String> sheetNames = new ArrayList<>();
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            sheetNames.add(workbook.getSheetName(i));
        }
        return sheetNames;
    }

    private String getCellData(Sheet sheet, int rowNum, int colNum) {
        if (sheet == null) return "Sheet not found";
        Row row = sheet.getRow(rowNum);
        return getCellData(row, colNum);
    }

    private String getCellData(Row row, int colNum) {
        if (row == null) return "";
        Cell cell = row.getCell(colNum, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
        if (cell == null) return "";

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "Unsupported Cell Type";
        }
    }

    // 🔄 Update specific cell by column name
    public void updateCell(String sheetName, int rowIndex, String columnName, String value) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            System.out.println("❌ Sheet not found: " + sheetName);
            return;
        }

        Row headerRow = sheet.getRow(0);
        if (headerRow == null) {
            System.out.println("❌ Header row not found in sheet: " + sheetName);
            return;
        }

        int columnIndex = -1;
        for (Cell cell : headerRow) {
            if (cell.getStringCellValue().trim().equalsIgnoreCase(columnName.trim())) {
                columnIndex = cell.getColumnIndex();
                break;
            }
        }

        if (columnIndex == -1) {
            System.out.println("❌ Column '" + columnName + "' not found in header row.");
            return;
        }

        Row targetRow = sheet.getRow(rowIndex);
        if (targetRow == null) {
            targetRow = sheet.createRow(rowIndex);
        }

        Cell targetCell = targetRow.getCell(columnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        targetCell.setCellValue(value);
    }

    // 💾 Save changes to the Excel file and close
    public void saveAndClose() {
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            workbook.close();
            System.out.println("✅ Workbook saved and closed: " + filePath);
            System.out.flush();
        } catch (IOException e) {
            System.out.println("❌ Error saving workbook: " + e.getMessage());
            System.out.flush();
            e.printStackTrace();
        }
    }

    public void closeWorkbook() {
        try {
            if (workbook != null) {
                workbook.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




















//package preRequisites;
//
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.*;
//
//public class ExcelReader {
//
//    private String filePath;
//    private Workbook workbook;
//    private Properties pr;
//
//    // Constructor to load file path from config.properties and initialize the workbook
//    public ExcelReader(String filekey) {
//        loadProperties();
//        this.filePath = pr.getProperty(filekey);
//
//        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
//            workbook = new XSSFWorkbook(fileInputStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Load properties from config.properties
//    private void loadProperties() {
//        pr = new Properties();
//        try (FileInputStream fis = new FileInputStream("src/test/resources/configfiles/config.properties")) {
//            pr.load(fis);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Get data from a specific cell by sheet name
//    public String getDataBySheetName(String sheetName, int rowNum, int colNum) {
//        Sheet sheet = workbook.getSheet(sheetName);
//        return getCellData(sheet, rowNum, colNum);
//    }
//
//    // Get data from a specific cell by sheet index
//    public String getDataBySheetIndex(int sheetIndex, int rowNum, int colNum) {
//        Sheet sheet = workbook.getSheetAt(sheetIndex);
//        return getCellData(sheet, rowNum, colNum);
//    }
//
//    // Get the entire sheet data as a List of Maps by sheet name
//    public List<Map<String, String>> getSheetDataAsMap(String sheetName) {
//        Sheet sheet = workbook.getSheet(sheetName);
//        List<Map<String, String>> dataList = new ArrayList<>();
//
//        if (sheet == null) {
//            System.out.println("Sheet not found: " + sheetName);
//            return dataList;
//        }
//
//        Row headerRow = sheet.getRow(0); // First row contains headers
//        if (headerRow == null) {
//            System.out.println("Header row is missing in sheet: " + sheetName);
//            return dataList;
//        }
//
//        int columnCount = headerRow.getLastCellNum();
//
//        for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Start from row 1 (skip header)
//            Row row = sheet.getRow(i);
//            if (row == null) continue; // Skip empty rows
//
//            Map<String, String> dataMap = new HashMap<>();
//            for (int j = 0; j < columnCount; j++) {
//                Cell headerCell = headerRow.getCell(j, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
//                if (headerCell == null) continue; // Skip if header is missing
//
//                String header = headerCell.getStringCellValue().trim();
//                String cellValue = getCellData(row, j);
//                dataMap.put(header, cellValue);
//            }
//            dataList.add(dataMap);
//        }
//        return dataList;
//    }
//
//    // Get the entire sheet data as a List of Maps by sheet index
//    public List<Map<String, String>> getSheetDataAsMapByIndex(int sheetIndex) {
//        Sheet sheet = workbook.getSheetAt(sheetIndex);
//        List<Map<String, String>> dataList = new ArrayList<>();
//
//        if (sheet == null) {
//            System.out.println("Sheet index " + sheetIndex + " not found.");
//            return dataList;
//        }
//
//        Row headerRow = sheet.getRow(0); // First row contains headers
//        if (headerRow == null) {
//            System.out.println("Header row is missing in sheet index: " + sheetIndex);
//            return dataList;
//        }
//
//        int columnCount = headerRow.getLastCellNum();
//
//        for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Start from row 1 (skip header)
//            Row row = sheet.getRow(i);
//            if (row == null) continue; // Skip empty rows
//
//            Map<String, String> dataMap = new HashMap<>();
//            for (int j = 0; j < columnCount; j++) {
//                Cell headerCell = headerRow.getCell(j, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
//                if (headerCell == null) continue; // Skip if header is missing
//
//                String header = headerCell.getStringCellValue().trim();
//                String cellValue = getCellData(row, j);
//                dataMap.put(header, cellValue);
//            }
//            dataList.add(dataMap);
//        }
//        return dataList;
//    }
//
//    // Get all sheet names in the workbook
//    public List<String> getSheetNames() {
//        List<String> sheetNames = new ArrayList<>();
//        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
//            sheetNames.add(workbook.getSheetName(i));
//        }
//        return sheetNames;
//    }
//
//    // Get cell data from a given sheet
//    private String getCellData(Sheet sheet, int rowNum, int colNum) {
//        if (sheet == null) return "Sheet not found";
//        Row row = sheet.getRow(rowNum);
//        return getCellData(row, colNum);
//    }
//
//    // Extract data from a specific cell
//    private String getCellData(Row row, int colNum) {
//        if (row == null) return ""; // Handle missing rows
//        Cell cell = row.getCell(colNum, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
//        if (cell == null) return ""; // Handle missing cells
//
//        switch (cell.getCellType()) {
//            case STRING:
//                return cell.getStringCellValue().trim();
//            case NUMERIC:
//                if (DateUtil.isCellDateFormatted(cell)) {
//                    return cell.getDateCellValue().toString();
//                } else {
//                    return String.valueOf(cell.getNumericCellValue());
//                }
//            case BOOLEAN:
//                return String.valueOf(cell.getBooleanCellValue());
//            case FORMULA:
//                return cell.getCellFormula();
//            case BLANK:
//                return "";
//            default:
//                return "Unsupported Cell Type";
//        }
//    }
//
//    // Close workbook to free up resources
//    public void closeWorkbook() {
//        try {
//            if (workbook != null) {
//                workbook.close();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
