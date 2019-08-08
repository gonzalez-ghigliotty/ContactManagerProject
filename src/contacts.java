package src;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.StringTokenizer;

public class contacts {
    public static void main(String[] args) {

        Path contactPath = Paths.get("src/","contacts.txt");
        List<String> contactList = null;
        try {
            contactList = Files.readAllLines(contactPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.printf("%-15s|%-15s|", "     Name", " Phone Number");
        System.out.println();
        System.out.println("--------------------------------");

        for (int i = 0; i < contactList.size(); i ++) {
            StringTokenizer contactLine = new StringTokenizer(contactList.get(i));

            for (int ii = 0; contactLine.hasMoreElements(); ii++) {
                String token = contactLine.nextElement().toString();
                System.out.printf("%-15s|", token);
            }
            System.out.println();
        }
    }
}
