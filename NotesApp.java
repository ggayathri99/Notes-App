import java.io.*;
import java.util.Scanner;

public class NotesApp {

    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n==== NOTES MANAGER ====");
            System.out.println("1. Add Note (Append)");
            System.out.println("2. View Notes");
            System.out.println("3. Overwrite Notes");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter note: ");
                    String note = sc.nextLine();
                    writeNote(note, true);
                    break;

                case 2:
                    readNotes();
                    break;

                case 3:
                    System.out.print("Enter new content: ");
                    String newNote = sc.nextLine();
                    writeNote(newNote, false);
                    break;

                case 4:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 4);

        sc.close();
    }

    private static void writeNote(String content, boolean append) {

        try (FileWriter fw = new FileWriter(FILE_NAME, append)) {
            fw.write(content + System.lineSeparator());
            System.out.println("Note saved successfully!");
        }
        catch (IOException e) {
            System.out.println("Error writing file");
            e.printStackTrace();
        }
    }

    private static void readNotes() {

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;
            System.out.println("\n--- Your Notes ---");

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("No notes found yet!");
        }
        catch (IOException e) {
            System.out.println("Error reading file");
            e.printStackTrace();
        }
    }
}
