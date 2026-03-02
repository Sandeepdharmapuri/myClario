//package uTility;
//
//import java.io.PrintWriter;
//import java.io.StringWriter;
//
//import preRequisites.BaseTest;
//
//public class StepWrapper {
//    public static void runWithLogging(String stepDescription, Runnable stepLogic) {
//        try {
//            stepLogic.run();
//        } catch (Exception e) {
//            String agentName = BaseTest.currentAgentName;
//
//            // Capture full stack trace
//            StringWriter sw = new StringWriter();
//            PrintWriter pw = new PrintWriter(sw);
//            e.printStackTrace(pw);
//            String fullStackTrace = sw.toString();
//
//            BaseTest.failureDetailsMap.put(agentName,
//                "❌ Step: " + stepDescription +
//                "\n📌 Error: " + e.getMessage() +
//                "\n📄 Stack Trace:\n" + fullStackTrace);
//
//            throw e; // rethrow so test fails
//        }
//    }
//}
