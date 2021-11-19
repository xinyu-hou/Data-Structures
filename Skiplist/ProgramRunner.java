package SkiplistQuestion;

import java.io.InputStreamReader;
import java.util.Scanner;

public class ProgramRunner {

    /**
     * This function assumes that all the given commands are valid.
     * Valid commands include: insert, delete, lookup, and quit.
     *
     * @param rd a Readable object
     */
    public static void demo(SkiplistInterface list, Readable rd) {
        Scanner input = new Scanner(rd);
        boolean quit = false;
        System.out.println("Valid commands: insert, delete, lookup, quit.");
        while (!quit) {
            if (input.hasNext()) {
                String scriptCommand = input.nextLine();
                String[] scriptParts = scriptCommand.split("\\s+");
                Integer target = null;
                switch (scriptParts[0]) {
                    case "insert":
                        target = Integer.valueOf(scriptParts[1]);
                        System.out.println("Inserting " + target + "...");
                        list.insert(target);
                        break;

                    case "delete":
                        target = Integer.valueOf(scriptParts[1]);
                        System.out.println("Deleting " + target + "...");
                        list.delete(target);
                        break;

                    case "lookup":
                        target = Integer.valueOf(scriptParts[1]);
                        System.out.println("Searching " + target + "...");
                        System.out.println(target + " found: "+ list.search(target));
                        break;

                    case "quit":
                        quit = true;
                        break;
                }
                System.out.println(list.toString());
            } else {
                quit = true;
            }
        }
    }

    public static void main(String[] args) {
        // Construct a skiplist
        SkiplistInterface l1 = new Skiplist();
        Readable rd = new InputStreamReader(System.in);
        demo(l1, rd);
    }

}
