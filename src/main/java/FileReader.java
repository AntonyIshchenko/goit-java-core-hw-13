import java.io.File;
import java.util.Scanner;

public class FileReader {
    public static String getFileContent(String filePath) {
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("File not found! Can't do task!");
            return null;
        }

        if (file.isDirectory()) {
            System.out.println("File is a directory! Can't do task!");
            return null;
        }

        StringBuilder result = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                result.append(scanner.nextLine());
            }

            return result.toString();

        } catch (Exception ex) {
            System.out.println("Error while reading file:");
            ex.printStackTrace();
            return null;
        }

    }
}
