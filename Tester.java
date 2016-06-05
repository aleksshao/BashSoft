package BashSoft;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ALEKSANDAR on 5/25/2016.
 */
public class Tester {
    private static String getMismatchPath(String expectedOutput) {
        int index = expectedOutput.lastIndexOf('\\');
        String directoryPath = expectedOutput.substring(0, index);
        return directoryPath + "\\mismatch.txt";
    }

//    public static void main(String[] args) {
//        String test1path = "C:\\Users\\ALEKSANDAR\\Desktop\\test1.txt";
//        String test2path = "C:\\Users\\ALEKSANDAR\\Desktop\\test2.txt";
//        Tester.compareContent(test1path, test2path);
//    }
public static void compareContent(String actualOutput, String expectedOutput) {
    try{
        OutputWriter.writeMessageOnNewLine("Reading files ...");
        String mismatchPath = getMismatchPath(expectedOutput);
        List<String> actualOutputString = readTextFile(actualOutput);
        List<String> expectedOutputString = readTextFile(expectedOutput);
        boolean mismatch = compareStrings(actualOutputString,
                expectedOutputString,
                mismatchPath);
        if (mismatch){
            List<String> mismatchString = readTextFile(mismatchPath);
            mismatchString.forEach(OutputWriter::writeMessageOnNewLine);
        }else{
            OutputWriter.writeMessageOnNewLine("Files are identical. There are no mismatches");
        }
    }catch (IOException ex){
        OutputWriter.displayExeption(ExceptionMessages.FILE_NOT_FOUND);
    }

}

    private static boolean compareStrings(List<String> actualOutputString,
                                          List<String> expectedOutputString,
                                          String mismatchPath) {
        OutputWriter.writeMessageOnNewLine("Comparing files...");
        String output = "";
        boolean isMismatch = false;
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(mismatchPath))) {
            int maxLength = Math.max(actualOutputString.size(), expectedOutputString.size());
            for (int i = 0; i < expectedOutputString.size(); i++) {
                String actualLine = actualOutputString.get(i);
                String expectedLine = expectedOutputString.get(i);

                if (!actualLine.equals(expectedLine)) {
                    writer.write(String.format("mismatch -> expected{%s}, actual{%s}%n", expectedLine, actualLine));
                    isMismatch = true;
                } else {
                    writer.write(String.format("line match -> %s%n", actualLine));
                }
            }
        } catch (Exception e) {
            isMismatch = true;
            OutputWriter.displayExeption(ExceptionMessages.INVALID_OUTPUT_LENGTH);
        }
        return isMismatch;
    }
    private static List<String> readTextFile (String filePath) throws IOException{
        List<String> text = new ArrayList<>();
        File file = new File(filePath);
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (true){
                if (line == null){
                    break;
                }
                text.add(line);
                line = reader.readLine();
            }
        }
        return text;
    }
}


