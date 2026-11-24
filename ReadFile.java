import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class ReadFile {

    public static ArrayList<PriceRecord> read(String ticker, String startingDate, String endingDate) {

        ArrayList<PriceRecord> csvData = new ArrayList<>();
        try {
            String path = "data/Market data_2025/" + ticker + "_2025.csv";
            File file = new File(path);
            if (!file.exists()) {
                System.out.println("Error: Ticker not found or file missing.");
                return new ArrayList<>();
            }
            BufferedReader csvReader =
                    new BufferedReader(new FileReader(path));
            String line;
            csvReader.readLine();
            while ((line = csvReader.readLine()) != null) {
                String[] row = line.split(",");
                if (row.length < 6) {
                    System.out.println(
                            "Warning: Skipping malformed row: " + line);

                    continue;
                }
                String date = row[0].trim();
                if (date.compareTo(startingDate) >= 0 &&
                        date.compareTo(endingDate) <= 0) {
                    PriceRecord record =
                            new PriceRecord(
                                    row[0].trim(),
                                    Double.parseDouble(row[1]),
                                    Double.parseDouble(row[2]),
                                    Double.parseDouble(row[3]),
                                    Double.parseDouble(row[4]),
                                    Long.parseLong(row[5])
                            );
                    csvData.add(record);
                }
            }
            csvReader.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return csvData;
    }
}