package herd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDate;

public class Driver {
    private static ArrayList<StateStatistic> stateStats = new ArrayList<StateStatistic>();
    private static DataSet data = new DataSet();
    public static void main(String[] args) {
        long test = 12345;
        loadStatistics(data, "src/herd/herdManagement.csv", 3);
        System.out.println(data.getStats().toString());
    }
    /**
     * 
     * @author Jimmy McCarry
     * @param data DataSet to store information from the file
     * @param file name of file to be read 
     * @param numColumns Number of columns in the file
     */
    public static void loadStatistics(DataSet data, String file, int numColumns) {
        String delimeter = "[, ]+"; 
        String currentLine;
        List<List<String>> allLines = new ArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            for (int i = 0; i < numColumns; i++) br.readLine();
            while ((currentLine = br.readLine()) != null) {
                List<String> values = Arrays.asList(currentLine.split(delimeter));
                allLines.add(values);
            }
            allLines.forEach(l -> {
                StateStatistic statestat = new StateStatistic(State.valueOf(l.get(0)), Long.parseLong(l.get(1)), Long.parseLong(l.get(2)), 
                Long.parseLong(l.get(3)), Long.parseLong(l.get(4)), Long.parseLong(l.get(5)), Long.parseLong(l.get(6)));
                data.addStatistic(statestat);
                data.getStats().forEach(stat -> {
                    ((StateStatistic) stat).getState();
                });
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void displayStatistics(DataSet data) {
        int horses = 0;
        int burros = 0;
        // todo
     //   System.out.println("There are " + horses + " horses and " + burros + "burros in " + state);
    }
}
