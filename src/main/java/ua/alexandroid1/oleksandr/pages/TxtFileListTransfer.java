package ua.alexandroid1.oleksandr.pages;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Oleksandr on 05.01.2017.
 */
public class TxtFileListTransfer extends PropLoader {

    protected static void fileToList(List<String> appliedList, String src) {
        Scanner s = null;
        try {
            s = new Scanner(new File(src));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (s.hasNext()) {
            appliedList.add(s.next());
        }
        s.close();
    }

    protected void listToFile(List<String> appliedList, String src) {
        try {
            Files.write(Paths.get(src), appliedList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
