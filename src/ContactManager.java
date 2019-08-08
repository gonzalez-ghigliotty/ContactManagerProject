import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

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
            System.out.println("Press '0' to return to menu.");
            String back = scan.next();
            if (back.contains("0")); {
                main(null);
            }
        } else if (response.contains("2")) {
            String newContact;
            System.out.println("Enter name of contact.");
            String newName = scan.next();
            System.out.println("Enter phone number of contact.");
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
            String newSearch;
            System.out.println("Search for your contact.");
            newSearch = scan.next();
            Path contactPath = Paths.get("src/","Contacts.txt");
            List<String> contactList = null;
            try {
                contactList = Files.readAllLines(contactPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            List<String> filteredList = null;


            for (int i = 0; i < contactList.size(); i ++) {
                StringTokenizer contactLine = new StringTokenizer(contactList.get(i));
                String token = contactLine.nextElement().toString();
                if (token.contains(newSearch)) {
                    for (int ii = 0; contactLine.hasMoreElements(); ii++) {
                        String token2 = contactLine.nextElement().toString();
                        System.out.printf("%-15s|", token2);
                    }
                }


                System.out.println(token);
            }
        }
    }
}
