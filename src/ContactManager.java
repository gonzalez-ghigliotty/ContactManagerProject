import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ContactManager {
    public static void main(String[] args) {

        boolean addedContact = false;
        System.out.println("1. View Contacts.\uD83D\uDC40\n" +
                "2. Add a new contact.➕\n" +
                "3. Search a contact by name.\uD83D\uDD0D\n" +
                "4. Delete an existing contact.\uD83D\uDEB7\n" +
                "5. Exit.✌️\n" +
                "Enter an option (1, 2, 3, 4 or 5):");
        Scanner scan = new Scanner(System.in);
        String response = scan.nextLine();


//          TODO: Create a if to output the contacts.txt file. and to return to the main menu.
        if (response.equals("1")) {
            Contacts.main();
            System.out.println();
            System.out.println("Press any key to return to menu.");
            String back = scan.next();
                main(null);


//           TODO: ADD a else if to allow the user to add a contact.
        } else if (response.equals("2")) {
            String newContact;
            System.out.println("Enter first name of contact: ");
            String newName = scan.next();
            System.out.println("Enter phone number of contact: ");
            String newNumber = scan.next();
            newContact = newName + " " + newNumber;


//            TODO: Create the try/catch to avoid getting errors and to prompt the user of existing contact.

            Path contactPath = Paths.get("src/", "Contacts.txt");
            List<String> contactList = null;
            try {
                contactList = Files.readAllLines(contactPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < contactList.size(); i++) {
                String checkName = contactList.get(i);
                String[] checkSplitName = checkName.split(" ");
                if (checkSplitName[0].equalsIgnoreCase(newName)) {
                    System.out.printf("There's already a contact named %s. Do you want to overwrite it? (Yes/No)", newName);
                    String replaceConfirm = scan.next();
                    if (replaceConfirm.equalsIgnoreCase("yes")) {

                        try {
                            contactList.remove(contactList.get(i));
                            PrintWriter writer = new PrintWriter("src/contacts.txt");
                            for (int ii = 0; ii < contactList.size(); ii++)
                                writer.println(contactList.get(ii));
                            writer.close();
                            Files.write(
                                    Paths.get("src/", "Contacts.txt"),
                                    Arrays.asList(newContact), // list with one item
                                    StandardOpenOption.APPEND
                            );
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        addedContact = true;
                        System.out.println("Contact successfully replaced");
                        main(null);
                        break;
                    } else if (replaceConfirm.equalsIgnoreCase("n")) {
                        main(null);
                        break;
                    } else {
                        System.out.println("Please enter a valid answer");
                    }
                }
            }
            if (!addedContact) {
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
            }


//            TODO: Allow the user to search for a contact and if more than one contact share a same character, an array list will drop down.
        } else if (response.equals("3")) {
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

                System.out.printf("%-15s| %-15s|", "     Name", " Phone Number");
                System.out.println();
                System.out.println("--------------------------------");
                for (int i = 0; i < contactList.size(); i++) {
                    String checkName = contactList.get(i);
                    String[] filterSplitName = checkName.split(" ");
                    if (filterSplitName[0].toLowerCase().contains(newSearch.toLowerCase())) {
                        System.out.printf("%-15s| %-15s|", filterSplitName[0], filterSplitName[1]);
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


//            TODO: Prompt the user about deleting a contact and making sure the input is a actual contact.
        } else if (response.equals("4")) {
            System.out.println("Who do you wish to shun?");
            String delete = scan.next();
            Path contactPath = Paths.get("src/", "Contacts.txt");
            List<String> contactList = null;
            try {
                contactList = Files.readAllLines(contactPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            boolean foundSomeone = true;
            for (int i = 0; i < contactList.size(); i++) {
                String checkName = contactList.get(i);
                String[] deleteSplitName = checkName.split(" ");
                if (deleteSplitName[0].toLowerCase().contains(delete.toLowerCase())) {
                    System.out.printf("%-15s| %-15s|", deleteSplitName[0], deleteSplitName[1]);
                    System.out.println();
                    System.out.println("Are you sure you wish to shun contact?");
                    String confirm = scan.next();
                    if (confirm.contains("y")) {
                        try {
                            contactList.remove(contactList.get(i));
                            PrintWriter writer = new PrintWriter("src/contacts.txt");
                            for (int ii = 0; ii < contactList.size(); ii++)
                                writer.println(contactList.get(ii));
                            writer.close();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println(deleteSplitName[0] + " has been shunned.");
                        System.out.println("Press any key to return to menu.");
                        String back = scan.next();
                        main(null);
                    } else {
                        System.out.println("Ok, " + deleteSplitName[0] + " won't be shunned.");
                        System.out.println("Press any key to return to menu.");
                        String back = scan.next();
                        main(null);
                    }
                }
                if (!deleteSplitName[0].contains(delete)) {
                    foundSomeone = false;
                }
            } if (!foundSomeone) {
                System.out.println("No contact was found. Unable to complete shun.");
                System.out.println("Press any key to return to menu.");
                String back = scan.next();
                main(null);
            }


//            TODO: Create a functioning exit option.
        } else if (response.equals("5")) {
            System.out.println("LATERSSSSSSSSSSS \uD83E\uDD2A");
            System.exit(0);


//            TODO: In case user doesn't put a valid answer.
        } else {
            System.out.println("That's not a valid answer.");
            System.out.println();
            System.out.println();
            main(null);
        }
    }
}
