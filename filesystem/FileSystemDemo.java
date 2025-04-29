package filesystem;

import java.util.Scanner;

public class FileSystemDemo {
    public static void main(String[] args) {
        FileSystemManager fileSystemManager = new FileSystemManager();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Enter command:");
            String input = scanner.nextLine().trim();
            String[] parts = input.split(" ", 3);
            if (parts.length == 0) {
                continue;
            }
            String command = parts[0].toLowerCase();
            try {
                switch(command) {
                    case "mkdir":
                        System.out.println("here");
                        if (parts.length >= 2) {
                            String path = parts[1];
                            boolean isCreated = fileSystemManager.createPath(path);
                            System.out.println(isCreated ? "Path is created successfully!" : "Failed to create path!");
                        } else {
                            System.out.println("mkdir <path> - create a new path");
                        }
                        break;
                    case "cd":
                        if (parts.length >= 2) {
                            String path = parts[1];
                            boolean isDirectory = fileSystemManager.getDirectory(path);
                            System.out.println(isDirectory ? "cd successfully to directory" + fileSystemManager.getCurrDir().getName() : "Failed to cd!");
                        } else {
                            System.out.println("cd <path> - go the directory");
                        }
                        break;
                    case "ls":
                        if (parts.length >= 2) {
                            String path = parts[1];
                            fileSystemManager.display(path);
                        } else {
                            System.out.println("ls <path> - list all children");
                        }
                        break;
                    //case "read":
                        //System.out.println("mkdir <path> - create a new path");
                    //case "write":
                        //System.out.println("mkdir <path> - create a new path");
                    case "exit":
                        // Exit the program
                        isRunning = false;
                        System.out.println("Exiting...");
                        break;
                    default:
                        // Handle unknown commands
                        System.out.println("Unknown command. Available commands: mkdir, cd");
                    }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
