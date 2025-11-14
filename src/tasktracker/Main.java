package tasktracker;
import tasktracker.models.User;
import tasktracker.services.UserService;
import tasktracker.services.TaskService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UserService userService = new UserService();
        TaskService taskService = new TaskService();

        System.out.println("Welcome to Task Tracker!");
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        User currentUser = userService.login(username, password);

        if (currentUser == null) {
            System.out.println("Invalid username or password. Exiting...");
            return;
        }

        System.out.println("Login successful! Welcome " + currentUser.getUsername());

        if (currentUser.getRole().equalsIgnoreCase("admin")) {
            adminMenu(sc, userService, taskService);
        } else if (currentUser.getRole().equalsIgnoreCase("member")) {
            memberMenu(sc, currentUser, taskService);
        } else {
            System.out.println("Invalid role. Exiting...");
        }
    }

    // Admin menyusu
    private static void adminMenu(Scanner sc, UserService userService, TaskService taskService) {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add User");
            System.out.println("2. Delete User");
            System.out.println("3. List Users");
            System.out.println("4. Add Task");
            System.out.println("5. List All Tasks");
            System.out.println("6. Logout");
            System.out.print("Choose option: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter username: ");
                    String uname = sc.nextLine();
                    System.out.print("Enter password: ");
                    String pwd = sc.nextLine();
                    System.out.print("Enter role (admin/member): ");
                    String role = sc.nextLine();
                    userService.addUser(uname, pwd, role);
                    break;
                case "2":
                    System.out.print("Enter username to delete: ");
                    String delUser = sc.nextLine();
                    userService.deleteUser(delUser);
                    break;
                case "3":
                    userService.listUsers();
                    break;
                case "4":
                    System.out.print("Task ID: ");
                    String taskId = sc.nextLine();
                    System.out.print("Assign to (username): ");
                    String assignedTo = sc.nextLine();
                    System.out.print("Description: ");
                    String desc = sc.nextLine();
                    System.out.print("Deadline (yyyy-mm-dd): ");
                    String deadline = sc.nextLine();
                    taskService.addTask(taskId, assignedTo, desc, deadline);
                    break;
                case "5":
                    taskService.listAllTasks();
                    break;
                case "6":
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    // Member menyusu
    private static void memberMenu(Scanner sc, User currentUser, TaskService taskService) {
        while (true) {
            System.out.println("\n--- Member Menu ---");
            System.out.println("1. View My Tasks");
            System.out.println("2. Mark Task Completed");
            System.out.println("3. Add Progress Note");
            System.out.println("4. Logout");
            System.out.print("Choose option: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    taskService.listTasksForUser(currentUser.getUsername());
                    break;
                case "2":
                    System.out.print("Enter Task ID to mark completed: ");
                    String taskId = sc.nextLine();
                    taskService.markTaskCompleted(taskId);
                    break;
                case "3":
                    System.out.print("Enter Task ID: ");
                    String tId = sc.nextLine();
                    System.out.print("Enter progress note: ");
                    String note = sc.nextLine();
                    taskService.addProgress(currentUser.getUsername(), tId, note);
                    break;
                case "4":
                    System.out.println("Logging out...");
                    return;

            }
        }
    }
}