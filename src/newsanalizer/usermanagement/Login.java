package newsanalizer.usermanagement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

public class Login {

    private List<User> listUsers = loadDB();

    static Scanner scanner = new Scanner(System.in);

    public void doLogin() throws InterruptedException {

        // load from db the list of users

//        for(User u: listUsers)
//            System.out.println(u);

        // read from kb a user
        //while user from kb ! = a user in db stay here
        int maxTries = 1;
        boolean succes = false;
        User userKb = new User();
        do {
            System.out.print("username: ");
            String kbUsername = scanner.nextLine();
            System.out.print("pwd: ");
            String kbPwd = scanner.nextLine();
            System.out.print("analyst: ");
            String kbAnalyst = scanner.nextLine();
            System.out.print("admin: ");
            String kbAdmin = scanner.nextLine();

            userKb.setUsername(kbUsername);
            userKb.setPassword(kbPwd);
            userKb.setAnalyst(Boolean.parseBoolean(kbAnalyst));
            userKb.setAnalyst(Boolean.parseBoolean(kbAnalyst));

            for(User u: listUsers) {

                if(u.equalsUsers(userKb))
                {
                    System.out.println("ok, you are logged in now");
                    succes = true;
                }

            }
            maxTries--;
            if (maxTries == 0) {
                maxTries = 1;
                System.out.println("you have to wait 10 seconds before you can try again :) ");
                TimeUnit.SECONDS.sleep(10);
            }
        }
        while(!succes);
        // when loggend in print menu based on parameters
        printMenu(userKb.isAnalyst(), userKb.isAdmin());

    }

    static void printMenu(boolean analyst, boolean admin) {
        if (analyst){
            System.out.println("print news");
        }
        if (admin){
            printAdminMeniu();
        }
    }

    static void printAdminMeniu (){

        // selectare pentru:
        // 1- print news
        // 2 - add user => new method addUser()
        // 2.1 - addUser() => input for username, pwd, isAnalyst, isAdmin
    }
    static void printNews (){

    }

     void addUser(){

        User username = new User();

        System.out.print("username: ");
        String kbUsername = scanner.nextLine();
        System.out.print("pwd: ");
        String kbPwd = scanner.nextLine();

        System.out.print("is analyst: ");
        boolean isAnalyst = Boolean.parseBoolean(scanner.nextLine());
        username.setAnalyst(isAnalyst);

        System.out.print("admin: ");
        boolean isAdmin = Boolean.parseBoolean(scanner.nextLine());
        username.setAdmin(isAdmin);





    }
    private List<User> loadDB() {

        Path path= Paths.get("users.txt");
        List<User> listOfUsers = new ArrayList<>();
        try {
            List<String> listOfUsersAsStrings= Files.readAllLines(path);
            System.out.println(listOfUsersAsStrings);

            for(int i = 0; i<listOfUsersAsStrings.size();i++) {
                User uObj = new User();
                String currentLineOfText = listOfUsersAsStrings.get(i);
                StringTokenizer st = new StringTokenizer(currentLineOfText, " ");
                while(st.hasMoreTokens()) {
                    String u = st.nextToken();
                    String p = st.nextToken();


                    uObj.setUsername(u.trim());
                    uObj.setPassword(p.trim());


                }
                listOfUsers.add(uObj);
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
        }

        return listOfUsers;
    }

}
