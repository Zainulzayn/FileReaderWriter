import java.io.*;
import java.util.Scanner;

public class FileReaderWriter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filePath = "data.txt"; // Specify your file path here

        // Read and process the file
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            int lineCount = 0;
            int wordCount = 0;
            int charCount = 0;

            while ((line = reader.readLine()) != null) {
                lineCount++;
                wordCount += line.split("\\s+").length; // Split by whitespace
                charCount += line.length();
            }
            reader.close();

            // Display the counts
            System.out.println("Number of lines: " + lineCount);
            System.out.println("Number of words: " + wordCount);
            System.out.println("Number of characters: " + charCount);

        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }

        // Allow the user to append data to the file
        System.out.println("Do you want to append new data to the file? (yes/no)");
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("yes")) {
            try {
                FileWriter writer = new FileWriter(filePath, true); // true for append mode
                System.out.println("Enter the text to append (type 'exit' to finish):");
                String input;
                while (!(input = scanner.nextLine()).equalsIgnoreCase("exit")) {
                    writer.write(input + System.lineSeparator());
                }
                writer.close();
                System.out.println("Data appended successfully.");
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the file.");
                e.printStackTrace();
            }
        }

        scanner.close();
    }
}

