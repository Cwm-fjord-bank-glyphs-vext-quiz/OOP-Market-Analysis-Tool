import javax.sound.midi.Synthesizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.nio.file.attribute.GroupPrincipal;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a ticker");
        String ticker = scanner.nextLine();

        System.out.println("Enter the starting date range (YYYY-MM-DD)");
        String startDate = scanner.nextLine();

        System.out.println("Enter the ending date range (YYYY-MM-DD)");
        String endDate = scanner.nextLine();

        if (!startDate.matches("\\d{4}-\\d{2}-\\d{2}") ||
                !endDate.matches("\\d{4}-\\d{2}-\\d{2}")) {

            System.out.println("Error: Dates must be in YYYY-MM-DD format.");
            return;
        }

        if (startDate.compareTo(endDate) > 0) {
            System.out.println("Error: Start date cannot be after end date.");
            return;
        }

        ArrayList<String> data = ReadFile.read(ticker, startDate, endDate);

        if (data.isEmpty()) {
            System.out.println("No data found for this ticker or date range.");
            return;
        }

        ArrayList<String> cleanedData = cleanUp.clean(data);

        if (cleanedData.isEmpty()) {
            System.out.println("Error: No valid data to analyze.");
            return;
        }

        double highestClosingPrice = calculations.highestClose(cleanedData);
        double lowestClosingPrice = calculations.lowestClose(cleanedData);
        double averageClosingPrice = calculations.averageClose(cleanedData);
        double priceChange = calculations.percentChange(cleanedData);
        double averageTradingVolume = calculations.averageTradingVolume(cleanedData);
        double bestTradingDay = calculations.percentageIncrease(cleanedData);
        double worstTradingDay = calculations.percentageDecrease(cleanedData);

        Formater formater = new Formater(
                ticker, startDate, endDate,
                highestClosingPrice, lowestClosingPrice,
                averageClosingPrice, priceChange,
                averageTradingVolume, bestTradingDay, worstTradingDay
        );
    }
}

class Formater {
    public Formater(String Ticker, String StartDate, String EndDate,
                    double highestClosingPrice, double lowestClosingPrice,
                    double averageClosingPrice, double priceChange,
                    double averageTradingVolume, double bestTradingDay,
                    double worstTradingDay) {

        System.out.println("Ticker: " + Ticker);
        System.out.println("Period: " + StartDate + " - " + EndDate);
        System.out.println();
        System.out.println("Highest Closing Price: " + highestClosingPrice);
        System.out.println("Lowest Closing Price: " + lowestClosingPrice);
        System.out.printf("Average Closing Price: %.2f%n", averageClosingPrice);
        System.out.printf("Price Change: %.2f%%%n", priceChange);
        System.out.printf("Average Trading Volume: %,d%n", (long) averageTradingVolume);
        System.out.println();
        System.out.printf("Best Trading Day: %.2f%%%n", bestTradingDay);
        System.out.printf("Worst Trading Day: %.2f%%%n", worstTradingDay);

    }
}

class ReadFile {

    public static ArrayList<String> read(String ticker, String startingDate, String endingDate) {

        ArrayList<String> csvData = new ArrayList<>();

        try {
            String path = "data/Market data_2025/" + ticker + "_2025.csv";

            File file = new File(path);
            if (!file.exists()) {
                System.out.println("Error: Ticker not found or file missing.");
                return new ArrayList<>();
            }

            BufferedReader csvReader = new BufferedReader(new FileReader(path));

            String line;

            csvReader.readLine(); // skip header

            while ((line = csvReader.readLine()) != null) {

                String[] row = line.split(",");

                if (row.length < 6) {
                    System.out.println("Warning: Skipping malformed row: " + line);
                    continue;
                }

                String date = row[0].trim();

                if (date.compareTo(startingDate) >= 0 &&
                        date.compareTo(endingDate) <= 0) {

                    csvData.add(line);
                }
            }

            csvReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return csvData;
    }
}

class cleanUp {
    public static ArrayList<String> clean(ArrayList<String> csvData) {

        ArrayList<String> open = new ArrayList<>();
        ArrayList<String> high = new ArrayList<>();
        ArrayList<String> low = new ArrayList<>();
        ArrayList<String> close = new ArrayList<>();
        ArrayList<String> volume = new ArrayList<>();
        ArrayList<String> masterList = new ArrayList<>();

        for (String data : csvData) {
            String[] info = data.split(",");

            open.add(info[1]);     // open
            high.add(info[2]);     // high
            low.add(info[3]);      // low
            close.add(info[4]);    // close
            volume.add(info[5]);   // volume
        }

        masterList.addAll(open);
        masterList.addAll(high);
        masterList.addAll(low);
        masterList.addAll(close);
        masterList.addAll(volume);

        return masterList;
    }
}

class calculations {

    public static double highestClose(ArrayList<String> cleanedData) {
        int size = cleanedData.size() / 5;
        double highestClose = 0;

        for (int i = 3 * size; i < 4 * size; i++) {
            double value = Double.parseDouble(cleanedData.get(i));
            if (value > highestClose) {
                highestClose = value;
            }
        }
        return highestClose;
    }

    public static double lowestClose(ArrayList<String> cleanedData) {
        int size = cleanedData.size() / 5;
        double lowestClose = 10000000;

        for (int i = 3 * size; i < 4 * size; i++) {
            double value = Double.parseDouble(cleanedData.get(i));
            if (value < lowestClose) {
                lowestClose = value;
            }
        }
        return lowestClose;
    }

    public static double averageClose(ArrayList<String> cleanedData) {
        int size = cleanedData.size() / 5;
        double avg = 0;

        for (int i = 3 * size; i < 4 * size; i++) {
            double value = Double.parseDouble(cleanedData.get(i));
            avg += value;
        }
        avg /= size;
        return avg;
    }

    public static double percentChange(ArrayList<String> cleanedData) {
        int size = cleanedData.size() / 5;
        int start = 3 * size;
        int end = (4 * size) - 1;

        double firstClose = Double.parseDouble(cleanedData.get(start));
        double lastClose = Double.parseDouble(cleanedData.get(end));
        double percentChange = ((lastClose - firstClose) / firstClose) * 100;

        return percentChange;
    }

    public static double averageTradingVolume(ArrayList<String> cleanedData) {
        int size = cleanedData.size() / 5;
        double avg = 0;

        for (int i = 4 * size; i < 5 * size; i++) {
            double value = Double.parseDouble(cleanedData.get(i));
            avg += value;
        }
        avg /= size;
        return avg;
    }

    public static double percentageIncrease(ArrayList<String> cleanedData) {
        int size = cleanedData.size() / 5;

        if (size < 2) {
            return 0;
        }

        double maxIncrease = -999999999;

        for (int i = (3 * size) + 1; i < 4 * size; i++) {
            double today = Double.parseDouble(cleanedData.get(i));
            double yesterday = Double.parseDouble(cleanedData.get(i - 1));
            double percent = ((today - yesterday) / yesterday) * 100;

            if (percent > maxIncrease) {
                maxIncrease = percent;
            }
        }

        return maxIncrease;
    }

    public static double percentageDecrease(ArrayList<String> cleanedData) {
        int size = cleanedData.size() / 5;

        if (size < 2) {
            return 0;
        }

        double maxDecrease = 999999999;

        for (int i = (3 * size) + 1; i < 4 * size; i++) {
            double today = Double.parseDouble(cleanedData.get(i));
            double yesterday = Double.parseDouble(cleanedData.get(i - 1));
            double percent = ((today - yesterday) / yesterday) * 100;

            if (percent < maxDecrease) {
                maxDecrease = percent;
            }
        }

        return maxDecrease;
    }
}
