import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ContactManager {
    public static void main(String[] args) {

        System.out.println("1. View Contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Delete an existing contact.\n" +
                "5. Exit.\n" +
                "Enter an option (1, 2, 3, 4 or 5):");
        Scanner scan = new Scanner(System.in);
        String response = scan.next();

        if (response.contains("1")) {
            Contacts.main();
            System.out.println();
            System.out.println("Press any key to return to menu.");
            String back = scan.next();
                main(null);
        } else if (response.contains("2")) {
            String newContact;
            System.out.println("Enter first name of contact: ");
            String newName = scan.next();
            System.out.println("Enter phone number of contact: ");
            String newNumber = scan.next();
            newContact = newName + " " + newNumber;

            try {
                Files.write(
                        Paths.get("src/", "Contacts.txt"),
                        Arrays.asList(newContact), // list with one item
                        StandardOpenOption.APPEND
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
            main(null);


        } else if (response.contains("3")) {
            do {
                String newSearch;
                System.out.println("Search for your contact.");
                newSearch = scan.next();
                Path contactPath = Paths.get("src/", "Contacts.txt");
                List<String> contactList = null;
                try {
                    contactList = Files.readAllLines(contactPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                List<String> filteredList = null;

                System.out.printf("%-15s|%-15s|", "     Name", " Phone Number");
                System.out.println();
                System.out.println("--------------------------------");
                for (int i = 0; i < contactList.size(); i++) {
                    String checkName = contactList.get(i);
//                System.out.println(checkName);
                    String[] filterSplitName = checkName.split(" ");
                    if (filterSplitName[0].contains(newSearch)) {
                        System.out.printf("%-15s|%-15s", filterSplitName[0], filterSplitName[1]);
                        System.out.println();
                    }
                }
                System.out.println();
                System.out.println("Would you like to search again? (y/n)");
                boolean asking = true;
                do {
                String back = scan.next();
                    if (back.contains("n")) {
                        main(null);
                        asking = false;
                    } else if (back.contains("y")) {
                        break;
                    } else {
                        System.out.println("Please give a valid answer.");
                    }
                } while (asking);
            } while (true);
        } else if (response.contains("4")) {

        }
    }
}
