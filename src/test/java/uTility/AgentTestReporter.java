//package uTility;
//
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import preRequisites.BaseTest;
//
//import java.io.*;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//public class AgentTestReporter {
//
//    public static void updateAgentSheet(String agentName, String testCaseId, boolean isPassed, String failureDetails, String llm) {
//
//        String excelPath = BaseTest.pr.getProperty("excel.filepath4");
//        String llmUsed = BaseTest.pr.getProperty("llm_used");
//
//        if (agentName == null || agentName.trim().isEmpty()) {
//            System.out.println("⚠️ Skipping update: Agent name is empty.");
//            System.out.flush();
//            return;
//        }
//
//        if (llmUsed == null || llmUsed.isEmpty()) {
//            llmUsed = "Unknown-LLM";
//        }
//
//        if ("TC_GS_AG_00".equals(testCaseId) || "FinalCleanup".equalsIgnoreCase(testCaseId)) {
//            System.out.println("⏭️ Skipping update for background/cleanup step: " + testCaseId);
//            System.out.flush();
//            return;
//        }
//
//        try (FileInputStream fis = new FileInputStream(excelPath);
//             Workbook workbook = new XSSFWorkbook(fis)) {
//
//            Sheet sheet = workbook.getSheetAt(0);
//            Row headerRow = sheet.getRow(0);
//
//            if (headerRow == null) {
//                System.out.println("❌ Header row missing.");
//                System.out.flush();
//                return;
//            }
//
//            Map<String, Integer> columnIndexMap = new HashMap<>();
//            for (Cell cell : headerRow) {
//                columnIndexMap.put(cell.getStringCellValue().trim(), cell.getColumnIndex());
//            }
//
//            boolean found = false;
//            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
//                Row row = sheet.getRow(i);
//                if (row == null) continue;
//
//                Cell testCaseCell = row.getCell(columnIndexMap.get("Testcase ID"), Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
//                if (testCaseCell != null && testCaseCell.getStringCellValue().trim().equalsIgnoreCase(testCaseId)) {
//                    found = true;
//
//                    try {
//                        System.out.println("🧪 Updating row for: " + agentName + " | Test ID: " + testCaseId);
//
//                        updateCell(row, columnIndexMap, "Testcase ID", testCaseId);
//                        updateCell(row, columnIndexMap, "Agent in staging", "Yes");
//                        updateCell(row, columnIndexMap, "Automation Script Implemented", "Yes");
//                        updateCell(row, columnIndexMap, "Dry Run in staging", "Yes");
//
//                        updateCell(row, columnIndexMap, "Automation Testcase Execution", isPassed ? "✅ Passed" : "❌ Failed");
//                        updateCell(row, columnIndexMap, "LLM", llmUsed);
//                        updateCell(row, columnIndexMap, "Status", isPassed ? "✅ Completed" : "❌ Pending");
//                        updateCell(row, columnIndexMap, "Completion Percentage", isPassed ? "100%" : "50%");
//
//                        String comment = BaseTest.failureDetailsMap.getOrDefault(agentName,
//                                isPassed ? "Process completed successfully ✅" : failureDetails);
//                        updateCell(row, columnIndexMap, "QA Comments", comment);
//
//                        updateCell(row, columnIndexMap, "Dev Comment/ Status", "");
//
//                        String timeStamp = new SimpleDateFormat("d/M/yyyy  h:mm:ss a").format(new Date());
//                        String remark = isPassed
//                                ? "✅ Auto updated on " + timeStamp
//                                : "❌ Auto update failed on " + timeStamp + " | See QA Comments for error trace.";
//                        updateCell(row, columnIndexMap, "Remark", remark);
//
//                        updateCell(row, columnIndexMap, "Last Tested", timeStamp);
//
//                        System.out.println("✅ Excel updated for Agent: " + agentName + " | Test ID: " + testCaseId);
//                        System.out.flush();
//
//                    } catch (Exception e) {
//                        System.err.println("❌ Error updating cells for Testcase ID " + testCaseId + ": " + e.getMessage());
//                        e.printStackTrace();
//                        System.out.flush();
//                    }
//                    break;
//                }
//            }
//
//            if (!found) {
//                System.out.println("⚠️ Agent/Testcase ID not found: " + agentName + " / " + testCaseId);
//                System.out.flush();
//            }
//
//            try (FileOutputStream fos = new FileOutputStream(excelPath)) {
//                workbook.write(fos);
//                System.out.println("📄 Excel file saved: " + excelPath);
//                System.out.flush();
//            } catch (IOException e) {
//                System.err.println("❌ Failed to save Excel file: " + e.getMessage());
//                e.printStackTrace();
//                System.out.flush();
//            }
//
//        } catch (FileNotFoundException fnf) {
//            System.err.println("❌ Excel file not found at path: " + excelPath);
//            fnf.printStackTrace();
//            System.out.flush();
//        } catch (IOException ioe) {
//            System.err.println("❌ IO Error while accessing Excel file: " + ioe.getMessage());
//            ioe.printStackTrace();
//            System.out.flush();
//        } catch (Exception ex) {
//            System.err.println("❌ Unexpected error: " + ex.getMessage());
//            ex.printStackTrace();
//            System.out.flush();
//        }
//    }
//
//    private static void updateCell(Row row, Map<String, Integer> columnIndexMap, String columnName, String value) {
//        if (!columnIndexMap.containsKey(columnName)) {
//            System.out.println("❌ Column not found in header: " + columnName);
//            System.out.flush();
//            return;
//        }
//        int colIndex = columnIndexMap.get(columnName);
//        Cell cell = row.getCell(colIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
//        cell.setCellValue(value);
//        // Optional: Enable text wrapping for long messages like stack trace
//        CellStyle wrapStyle = row.getSheet().getWorkbook().createCellStyle();
//        wrapStyle.setWrapText(true);
//        cell.setCellStyle(wrapStyle);
//    }
//}
