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
        System.out.println("1. View Contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Delete an existing contact.\n" +
                "5. Exit.\n" +
                "Enter an option (1, 2, 3, 4 or 5):");
        Scanner scan = new Scanner(System.in);
        String response = scan.nextLine();
        System.out.println(response);

        if (response.equals("1")) {
            Contacts.main();
            System.out.println();
            System.out.println("Press any key to return to menu.");
            String back = scan.next();
                main(null);
        } else if (response.equals("2")) {
            String newContact;
            System.out.println("Enter first name of contact: ");
            String newName = scan.next();
            System.out.println("Enter phone number of contact: ");
            String newNumber = scan.next();
            newContact = newName + " " + newNumber;

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

                System.out.printf("%-15s|%-15s|", "     Name", " Phone Number");
                System.out.println();
                System.out.println("--------------------------------");
                for (int i = 0; i < contactList.size(); i++) {
                    String checkName = contactList.get(i);
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
                if (deleteSplitName[0].contains(delete)) {
                    System.out.printf("%-15s|%-15s", deleteSplitName[0], deleteSplitName[1]);
                    System.out.println();
                    System.out.println("Are you sure you wish to shun contact?");
                    String confirm = scan.next();
                    try {
//                        Files.write(
                                contactList.remove(contactList.get(i));
                        PrintWriter writer = new PrintWriter("src/contacts.txt");
                        for (int ii = 0; ii < contactList.size(); ii++)
                            writer.println(contactList.get(ii));
                        writer.close();
//                                Paths.get("src/", "Contacts.txt"),
//                                Arrays.asList(deleteSplitName), // list with one item
//                                StandardOpenOption.
//                        );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Press any key to return to menu.");
                    String back = scan.next();
                    main(null);
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

        } else if (response.equals("5")) {
            System.out.println("LATERSSSSSSSSSSS \uD83E\uDD2A");
            System.exit(0);
        }
    }
}
