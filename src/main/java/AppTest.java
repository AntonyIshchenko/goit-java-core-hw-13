import java.io.File;
import java.util.Scanner;

public class AppTest {

    public static void main(String[] args) {
        App testApp = new App();

        System.out.println("********************************************");
        System.out.println("TASK 1 TESTS:");
        System.out.println("********************************************");
        System.out.println("getAllUsers() = " + testApp.getAllUsers());
        System.out.println("********************************************");
        System.out.println("getUserById(-5) = " + testApp.getUserById(-5));
        System.out.println("********************************************");
        System.out.println("getUserById(1) = " + testApp.getUserById(1));
        System.out.println("********************************************");
        System.out.println("getUsersByName(Moriah.Stanton) = " + testApp.getUsersByName("Moriah.Stanton"));
        System.out.println("********************************************");
        System.out.println("getUsersByName(kfnksdnjdo) = " + testApp.getUsersByName("kfnksdnjdo"));
        System.out.println("********************************************");
        System.out.println("deleteUser(1) = " + testApp.deleteUser(1));
        System.out.println("********************************************");
        String newUserData = getFileContent("./src/main/resources/newUser.json");
        System.out.println("createNewUser(newUser.json) = " + testApp.createNewUser(newUserData));
        System.out.println("********************************************");
        String updateUserData = getFileContent("./src/main/resources/updateUser.json");
        System.out.println("updateUser(1,newUser.json) = " + testApp.updateUser(1, updateUserData));
        System.out.println("********************************************");

        System.out.println("********************************************");
        System.out.println("TASK 2 TEST:");
        System.out.println("saveAllCommentsToUsersLastPost(1) = " + testApp.saveAllCommentsToUsersLastPost(1));
        System.out.println("********************************************");

        System.out.println("********************************************");
        System.out.println("TASK 3 TEST:");
        System.out.println("getAllTasksByUser(1,false) = " + testApp.getAllTasksByUser(1, false));
        System.out.println("********************************************");
    }

    private static String getFileContent(String filePath) {
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
