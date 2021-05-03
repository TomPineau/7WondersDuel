package Main;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static Main.Main.mainPath;

public class CSVReader {

    public static void printData(String csv) {
        BufferedReader br = null;
        String line;
        String cvsSplitBy = ";";
        try {
            String path = mainPath + "/src/CSV/" + csv + ".csv";
            br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                printStringList(data);
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

    public static String[] getRow(String csv, int index) {
        BufferedReader br = null;
        String line;
        String cvsSplitBy = ";";
        int rowIndex = 0;
        String[] row = new String[CSVReader.getColumnNumber(csv)];
        try {
            String path = mainPath + "/src/CSV/" + csv + ".csv";
            br = new BufferedReader(new FileReader(path));
            br.readLine();
            while (rowIndex != index) {
                if ((line = br.readLine()) != null) {
                    row = line.split(cvsSplitBy);
                    rowIndex++;
                }
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
        return row;
    }

    public static int getRowNumber(String csv) {
        BufferedReader br = null;
        String line;
        String cvsSplitBy = ";";
        int row = 0;
        try {
            String path = mainPath + "/src/CSV/" + csv + ".csv";
            br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                line.split(cvsSplitBy);
                row++;
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
        return row;
    }

    public static int getColumnNumber(String csv) {
        BufferedReader br = null;
        String line;
        String cvsSplitBy = ";";
        int column = 0;
        try {
            String path = mainPath + "/src/CSV/" + csv + ".csv";
            br = new BufferedReader(new FileReader(path));
            if ((line = br.readLine()) != null) {
                column = line.split(cvsSplitBy).length;
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
        return column;
    }

    private static void printStringList(@NotNull String[] stringList) {
        for (String string : stringList) {
            System.out.print("|" + string + "| ");
        }
        System.out.println();
    }

}
