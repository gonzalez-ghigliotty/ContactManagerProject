import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Contacts {
    private String name;
    private String number;
    public void Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName(String name) {return name;}
    public String getNumber(String number) {return number;}

    public static void main() {
                            // prints contact list
        Path contactPath = Paths.get("src/","Contacts.txt");
        List<String> contactList = null;
        try {
            contactList = Files.readAllLines(contactPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.printf("%-15s| %-15s|", "     Name", "Phone Number");
        System.out.println();
        System.out.println("--------------------------------");


        for (int i = 0; i < contactList.size(); i ++) {
            Contacts newContact = new Contacts();
            String newName = contactList.get(i);
            newContact.getName(newName);
            String[] splitName = newName.split(" ");
            System.out.printf("%-15s| %-15s|", splitName[0], splitName[1]);
            System.out.println();

        }
    }
}
