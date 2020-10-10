package pl.coderslab;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TaskManager {

    public static void main(String[] args) {

        mainMenu();

    }

    private static void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(ConsoleColors.BLUE + "Please select an option:" + ConsoleColors.RESET);

        String[] selectOption = {"add", "remove", "list", "exit"};
        for (String s : selectOption) {
            System.out.println(s);
        }
        String chosenOption = scanner.next();
        while (true) {
            if (!chosenOption.equals(selectOption[0]) && !chosenOption.equals(selectOption[1]) && !chosenOption.equals(selectOption[2]) && !chosenOption.equals(selectOption[3])) {
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

    private static void removeTask() {
        File file = new File("tasks.csv");
        StringBuilder reading = new StringBuilder();
        System.out.println(ConsoleColors.CYAN_BOLD + "Please select number to remove: " + ConsoleColors.RESET);
        Scanner numOfTask = new Scanner(System.in);
        while (!numOfTask.hasNextInt()) {
            numOfTask.next();
            System.out.println("Invalid data. Please provide a number.");
        }
        int taskNum = numOfTask.nextInt();
        int counter = 0;
        boolean lineIgnored = false;
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (counter != taskNum) {
                    reading.append(line).append("\n");
                } else {
                    lineIgnored = true;
                }
                counter++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("no file found");
        }
        try (FileWriter fileWriter = new FileWriter("tasks.csv", false)) {
            fileWriter.append(reading.toString());
        } catch (IOException e) {
            System.out.println("io exception");
        }
        if (lineIgnored){
            System.out.println("Value was successfully deleted.");
        } else {
            System.out.println("Value was not deleted.");
        }

        mainMenu();
    }

    private static void addTask() {
        Scanner scanner = new Scanner(System.in);
        StringBuilder newLine = new StringBuilder();
        System.out.println();
        System.out.println(ConsoleColors.CYAN_BOLD + "Please add task description: ");
        String description = scanner.nextLine();
        System.out.println("Please add task due date: ");
        String dueDate = scanner.nextLine();
        while (true) {
            if (dueDate.length() != 10) {
                System.out.println("Please insert date format YYYY-MM-DD");
                dueDate = scanner.nextLine();
            } else {
                break;
            }
        }
        System.out.println("Is your task important: true/false?: ");
        String importance = scanner.nextLine();
        while (true) {
            if (!importance.equals("true") && !importance.equals("false")) {
                System.out.println("Please insert true or false");
                importance = scanner.nextLine();
            } else {
                break;
            }
        }
        System.out.println(ConsoleColors.RESET);
        newLine.append(description).append(", ").append(dueDate).append(", ").append(importance).append("\n");
        try (FileWriter fileWriter = new FileWriter("tasks.csv", true)) {
            fileWriter.append(newLine);
        } catch (IOException e) {
            System.out.println("io exception");
        }

        mainMenu();
    }

    private static void showList() {
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

    private static void exit() {
        System.out.println();
        System.out.println(ConsoleColors.CYAN_BOLD + "EXIT");
        System.out.println(ConsoleColors.RED + "Bye, bye.");
        System.out.print(ConsoleColors.RESET);
    }
}