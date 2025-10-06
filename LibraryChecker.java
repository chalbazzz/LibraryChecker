import java.io.*;
import java.util.Scanner;

public class LibraryChecker{

    static final String FILE_NAME = "books.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Library Checker (with File) =====");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Book");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    viewBooks();
                    break;
                case 3:
                    searchBook(scanner);
                    break;
                case 4:
                    System.out.println("Exiting Library Checker. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 4);

        scanner.close();
    }

    static void addBook(Scanner scanner) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write(title + "\n");
            System.out.println("Book added successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    static void viewBooks() {
        System.out.println("\n--- Available Books ---");

        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No books found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int index = 1;
            while ((line = reader.readLine()) != null) {
                System.out.println(index + ". " + line);
                index++;
            }

            if (index == 1) {
                System.out.println("No books in the library.");
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    static void searchBook(Scanner scanner) {
        System.out.print("Enter book title to search: ");
        String searchTitle = scanner.nextLine();
        boolean found = false;

        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("Library is empty.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equalsIgnoreCase(searchTitle)) {
                    System.out.println("Book found: " + line);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Book not found.");
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
}