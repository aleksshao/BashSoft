package BashSoft;

import java.util.List;

public class OutputWriter {
//    public static void traverseDirectory(String path){
//        LinkedList<File> subFolders = new LinkedList<File>();
//        File root = new File(path);
//        subFolders.add(root);
//        while(subFolders.size()!=0){
//            File currentFolder = subFolders.removeFirst();
//            if (currentFolder.listFiles() != null){
//                for (File file : currentFolder.listFiles()) {
//                    if (file.isDirectory()){
//                        try {
//                            subFolders.add(file);
//                        } catch (IllegalAccessError ex) {
//                            OutputWriter.displayException("Access denied");
//                        }
//                    }
//                }
//
//            }
//            System.out.println(currentFolder.toString());
//        }
//    }

    public static void displayException(String message) {
        System.out.println(message);
    }
    public static void writeMessageOnNewLine(String message) {
        System.out.println(message);
    }public static void writeMessage(String message) {
        System.out.println(message);
    }
    public static void writeEmptyLine(){
        System.out.println();
    }
    public static void displayStudent(String studentName, List<Integer> studentMarks){
        String output = String.format("%s - %S", studentName, studentMarks.toString());
        OutputWriter.writeMessageOnNewLine(output);
    }
}
