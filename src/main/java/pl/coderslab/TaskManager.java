package pl.coderslab;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class TaskManager {

    public static void main(String[] args) {

        mainMenu();

    }
    private static void mainMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(ConsoleColors.BLUE + "Please select an option" + ConsoleColors.RESET);

        String[] selectOption = {"add", "remove", "list", "exit"};
        for (String s : selectOption) {
            System.out.println(s);
        }
        String chosenOption = scanner.next();
        while (true) {
            if (!chosenOption.equals(selectOption[0]) && !chosenOption.equals(selectOption[1]) && !chosenOption.equals(selectOption[2]) && !chosenOption.equals(selectOption[3])){
                System.out.println("Invalid option. Select again.");
                chosenOption = scanner.next();
            } else {
                break;
            }
        }
        switch (chosenOption) {
            case "add":
                addTask();
                break;
            case "remove":
                removeTask();
                break;
            case "list":
                showList();
                break;
            case "exit":
                exit();
                break;
        }
    }
    private static void removeTask(){
        mainMenu();
    }
    private static void addTask(){
        Path path = Paths.get("tasks.csv");
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println(ConsoleColors.CYAN_BOLD + "Please add task description: ");
        String description = scanner.nextLine();
        System.out.println(ConsoleColors.CYAN_BOLD + "Please add task due date: ");
        String dueDate = scanner.nextLine();
        System.out.println(ConsoleColors.CYAN_BOLD + "Is your task important: true/false?: ");
        String importance = scanner.nextLine();
        System.out.println(ConsoleColors.RESET);
        String[][] tasks = null;

        mainMenu();
    }
    private static void showList(){
        System.out.println();
        System.out.println(ConsoleColors.CYAN_BOLD + "List of current tasks" + ConsoleColors.RESET);
        File file = new File("tasks.csv");
        StringBuilder reading = new StringBuilder();
        int counter = 0;
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                reading.append(counter).append(" : ").append(scan.nextLine()).append("\n");
                counter++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("no file found");
        }
        System.out.println(reading.toString());
        mainMenu();
    }
    private static void exit(){
        System.out.println();
        System.out.println(ConsoleColors.CYAN_BOLD + "EXIT");
        System.out.println(ConsoleColors.RED + "Bye, bye.");
        System.out.print(ConsoleColors.RESET);
    }
}