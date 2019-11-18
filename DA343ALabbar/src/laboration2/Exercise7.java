package laboration2;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JOptionPane;

public class Exercise7 {

    private TreeMap<String, String> dictonary = new TreeMap<String, String>();
    
    public Exercise7(String filename) {
        readDictionary(filename,dictonary);
    }

    public static void readDictionary(String filename, Map<String, String> map) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename),"ISO-8859-1"));
            String[] parts;
            String swedish, skanska;
            String str = br.readLine();
            while (str != null) {
                parts = str.split(",");
                skanska = parts[0];
                swedish = parts[1];
                map.put(swedish, skanska);
                str = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println("readDictionary: " + e);
        }
    }

    public void list() {
        String strList = dictonary.toString(); // format: "{ eng1=sve1, eng2=sve2, ...}"
        strList = strList.substring(1, strList.length() - 1);
        String[] parts = strList.split(",");

        System.out.println("------------------------------------------------");
        for (String str : parts) {
            System.out.println(str);
        }
        System.out.println("------------------------------------------------");
    }
    
    public void translate() {
     	String swe;
    	String ska;
    	swe = JOptionPane.showInputDialog("Ange svenskt ord: ");
    	ska = dictonary.get(swe);
    	if(ska == null) {
    		JOptionPane.showMessageDialog(null, "ordet finns ej i listan");
    	}
    	else {
    		JOptionPane.showMessageDialog(null, swe + " blir på skånska: " + ska);
    	}
    }

    public static int menu(String[] options) {
        int res = 0;
        String input;
        String message = "VÄLJ ETT ALTERNATIV\n";
        for (int i = 0; i < options.length; i++) {
            message += "\n" + (i + 1) + ". " + options[i];
        }

        do {
            try {
                input = JOptionPane.showInputDialog(message);
                if (input == null) {
                    return 0;
                }
                res = Integer.parseInt(input);
            } catch (NumberFormatException e) {
            }
        } while (res < 1 || res > options.length);

        return res;
    }
    
    public String translate(String swe) {
		return dictonary.get(swe);
    }

    public static void main(String[] args) {
        Exercise7 ex7 = new Exercise7("files/SkSvEn.txt");
        String[] menuOptions = {"Översätt ord", "Skriv ut ordlista"};
        int choice = Exercise7.menu(menuOptions);
        while (choice != 0) {
            switch (choice) {
                case 1:
                    ex7.translate();
                    break;
                case 2:
                    ex7.list();
                    break;
            }
            choice = Exercise7.menu(menuOptions);
        }
    }
}
