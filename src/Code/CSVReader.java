package Code;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static Code.Main.mainPath;

class CSVReader {

    static void printData(String csvFile) {
        BufferedReader br = null;
        String line;
        String cvsSplitBy = ";";
        try {
            br = new BufferedReader(new FileReader(mainPath + csvFile));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                //printStringList(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void printStringList(@NotNull String[] stringList) {
        for (String string : stringList) {
            System.out.print("|" + string + "| ");
        }
        System.out.println();
    }

}
