
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
        String newUserData = FileReader.getFileContent("./src/main/resources/newUser.json");
        System.out.println("createNewUser(newUser.json) = " + testApp.createNewUser(newUserData));
        System.out.println("********************************************");
        String updateUserData = FileReader.getFileContent("./src/main/resources/updateUser.json");
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

}
