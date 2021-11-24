package HashQuestion;

import java.io.*;
import java.util.*;

public class ProgramRunner {

    public static String loadFile(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        FileReader input = new FileReader(filePath);
        BufferedReader in = new BufferedReader(input);
        String line;
        while ((line = in.readLine()) != null) {
            sb.append(line);
            sb.append(" ");
        }
        return sb.toString();
    }

    public static Hash saveWordAndCount(String text, Hash table) {
        text = text.toLowerCase();
        String[] words = text.split("\\s+");
        for (String word : words) {
            word = word.replaceAll("[^a-zA-Z]", "");
            table.increase(word);
        }
        return table;
    }

    public static String listWordAndCount(Hash table) {
        StringBuilder sb = new StringBuilder();
        List<String> keys = table.listAllKeys();
        for (String key : keys) {
            sb.append(key);
            sb.append("=");
            sb.append(table.find(key));
            sb.append(", ");
        }
        String result = sb.toString();
        if (result.length() > 1) {
            return result.substring(0, result.length() - 2);
        }
        return result;
    }

    public static void demo(HashInterface table, Readable rd) throws IOException {
        Scanner input = new Scanner(rd);
        boolean quit = false;
        System.out.println("Valid commands: insert, delete, increase, find, keys, print, save, quit.");
        while (!quit) {
            if (input.hasNext()) {
                String scriptCommand = input.nextLine();
                String[] scriptParts = scriptCommand.split("\\s+");
                switch (scriptParts[0]) {
                    case "insert":
                        String insertKey = scriptParts[1];
                        Integer insertVal = Integer.valueOf(scriptParts[2]);
                        System.out.println("Inserting " + insertKey + " with value " + insertVal + "...");
                        table.insert(insertKey, insertVal);
                        break;

                    case "delete":
                        String deleteKey = scriptParts[1];
                        System.out.println("Deleting " + deleteKey + "...");
                        table.delete(deleteKey);
                        break;

                    case "increase":
                        String increaseKey = scriptParts[1];
                        System.out.println("Increasing " + increaseKey + " by 1...");
                        table.increase(increaseKey);
                        break;

                    case "find":
                        String findKey = scriptParts[1];
                        System.out.println("Searching for " + findKey + "...");
                        Integer findResult = table.find(findKey);
                        if (findResult == null) {
                            System.out.println(findKey + " not found.");
                        } else {
                            System.out.println(findKey + " with value of " + findResult + " found.");
                        }
                        break;

                    case "keys":
                        List<String> allKeys = table.listAllKeys();
                        for (String key : allKeys) {
                            System.out.print(key + " ");
                        }
                        System.out.println();
                        break;

                    case "print":
                        System.out.println(table.toString());
                        break;

                    case "save":
                        String outputFilePath = ""; // insert the output file path here
                        FileWriter output = new FileWriter(outputFilePath);
                        BufferedWriter writer = new BufferedWriter(output);
                        writer.write(listWordAndCount((Hash) table));
                        writer.close();
                        break;

                    case "quit":
                        quit = true;
                        break;
                }
            } else {
                quit = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String filePath = ""; // insert the file path here
        String text = "";
        try {
            text = loadFile(filePath);
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Hash table = new Hash(5);
        saveWordAndCount(text, table);
        Readable rd = new InputStreamReader(System.in);
        demo(table, rd);
    }
}
