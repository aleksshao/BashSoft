package BashSoft;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by ALEKSANDAR on 5/28/2016.
 */
public class StudentRepository {
    public static boolean isDataInitialized = false;
    public static HashMap<String, HashMap<String, ArrayList<Integer>>> studentByCourse;

    public static void initializeData(String fileName) {
        if (isDataInitialized) {
            System.out.println(ExceptionMessages.DATA_ALREADY_INITIALIZED);
            return;
        }
        studentByCourse = new HashMap<String, HashMap<String, ArrayList<Integer>>>();
        try{
            readData(fileName);
            isDataInitialized = true;
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public static void readData(String fileName)throws IOException{
        String path = SessionData.currentPath + "\\resources\\" + fileName;
        List<String> lines = Files.readAllLines(Paths.get(path));

        for (String line : lines) {
            String[] tokens = line.split("\\s+");
            String course = tokens[0];
            String student = tokens[1];
            Integer mark = Integer.parseInt(tokens[2]);
            if (!studentByCourse.containsKey(course)){
                studentByCourse.put(course, new HashMap<>());
            }
            if (!studentByCourse.get(course).containsKey(student)){
                studentByCourse.get(course).put(student, new ArrayList<>());
            }
            studentByCourse.get(course).get(student).add(mark);
        }
        OutputWriter.writeMessageOnNewLine("Data read");
    }
    private static boolean isQueryForCoursePossible(String courseName){
        if (!isDataInitialized){
            OutputWriter.displayException(ExceptionMessages.DATA_NOT_INITIALIZED);
            return false;
        }
        if (!studentByCourse.containsKey(courseName)){
            OutputWriter.displayException(ExceptionMessages.NON_EXISTING_COURSE);
        }
        return true;
    }
    private static boolean isQueryForStudentPossible(String courseName, String studentName){
        if (!isQueryForCoursePossible(courseName)){
            return false;
        }
        if (!studentByCourse.get(courseName).containsKey(studentName)){
            OutputWriter.displayException(ExceptionMessages.NON_EXISTING_STUDENT);
            return false;
        }
       return true;
    }
    public static void getStudentMarksInCourse(String course, String student){
        if (!isQueryForStudentPossible(course, student)){
            return;
        }
        ArrayList<Integer> marks = studentByCourse.get(course).get(student);
        OutputWriter.displayStudent(student, marks);
    }
    public static void getStudentByCourse(String course){
        if (!isQueryForCoursePossible(course)){
            return;
        }
        OutputWriter.writeMessageOnNewLine(course + ":");
        for (Map.Entry<String, ArrayList<Integer>> student : studentByCourse.get(course).entrySet()) {
            OutputWriter.displayStudent(student.getKey(), student.getValue());
        }
    }


}
