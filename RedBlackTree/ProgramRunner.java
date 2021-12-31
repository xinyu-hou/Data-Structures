package RBTreeQuestion;

import java.io.*;
import java.util.Scanner;

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

    public static Integer[] extractArrayFromText(String text) {
        String[] strArray = text.split("\\s+");
        int length = strArray.length;
        Integer[] result = new Integer[length];
        for (int i = 0; i < length; i++) {
            result[i] = Integer.valueOf(strArray[i]);
        }
        return result;
    }

    public static void demo(RedBlackTree tree, Readable rd) {
        Scanner input = new Scanner(rd);
        boolean quit = false;
        System.out.println("Valid commands: insert, search, min, max, " +
                "successor, predecessor, sort, print, quit.");
        while (!quit) {
            if (input.hasNext()) {
                String scriptCommand = input.nextLine();
                String[] scriptParts = scriptCommand.split("\\s+");
                Integer target = null;
                switch (scriptParts[0]) {
                    case "insert":
                        target = Integer.valueOf(scriptParts[1]);
                        System.out.println("Inserting " + target + "...");
                        tree.insert(target);
                        break;

                    case "search":
                        target = Integer.valueOf(scriptParts[1]);
                        System.out.println("Searching " + target + "...");
                        Node searchResult = tree.search(target);
                        if (searchResult == tree.TNULL) {
                            System.out.println("Target not found.");
                        } else {
                            System.out.println("Target " + searchResult.key + " found.");
                        }
                        break;

                    case "min":
                        if (tree.root == tree.TNULL) {
                            System.out.println("No nodes in tree yet. Insert something first.");
                        } else {
                            Node min = tree.minimum(tree.root);
                            System.out.println("Tree minimum: " + min.key);
                        }
                        break;

                    case "max":
                        if (tree.root == tree.TNULL) {
                            System.out.println("No nodes in tree yet. Insert something first.");
                        } else {
                            Node max = tree.maximum(tree.root);
                            System.out.println("Tree maximum: " + max.key);
                        }
                        break;

                    case "successor":
                        target = Integer.valueOf(scriptParts[1]);
                        System.out.println("Looking for successor of " + target + "...");
                        Node successor = tree.successor(tree.search(target));
                        if (successor == null) {
                            System.out.println("No successor found.");
                        } else {
                            System.out.println("Successor: " + successor.key);
                        }
                        break;

                    case "predecessor":
                        target = Integer.valueOf(scriptParts[1]);
                        System.out.println("Looking for predecessor of " + target + "...");
                        Node predecessor = tree.predecessor(tree.search(target));
                        if (predecessor == null) {
                            System.out.println("No predecessor found.");
                        } else {
                            System.out.println("Predecessor: " + predecessor.key);
                        }
                        break;

                    case "sort":
                        System.out.println(tree.inorder());
                        break;

                    case "print":
                        tree.print(tree.root);
                        break;

                    case "quit":
                        quit = true;
                        break;
                }
                System.out.println("Current tree height: " + tree.getHeight());
            } else {
                quit = true;
            }
        }
    }

    public static void main(String[] args) {
        // Read in a file
        String currentDirectory = System.getProperty("user.dir");
        String filePath = currentDirectory + "RedBlackTree/sample.txt";
        String text = "";
        try {
            text = loadFile(filePath);
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Integer[] arr = extractArrayFromText(text);
        RedBlackTree tree1 = new RedBlackTree();
        // Build the tree by sequence of "inserts"
        for (Integer i : arr) {
            tree1.insert(i);
        }
        System.out.println("Tree structure after reading in the file: ");
        tree1.print(tree1.root);
        // Then interactively ask user for operational commands
        // After each operation prints out the height of the tree
        Readable rd = new InputStreamReader(System.in);
        demo(tree1, rd);
    }
}
