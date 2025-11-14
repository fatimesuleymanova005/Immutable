package tasktracker.services;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    // users.txt faylını oxuyur
    public static List<String> readFile(String filename) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + filename);
        }
        return lines;
    }

    // Fayla yazmaq (append)
    public static void writeFile(String filename, String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            bw.write(content);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error writing file: " + filename);
        }
    }

    // Faylı tam yenidən yazmaq (overwrite)
    public static void overwriteFile(String filename, List<String> contents) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (String line : contents) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error overwriting file: " + filename);
        }
    }
}
