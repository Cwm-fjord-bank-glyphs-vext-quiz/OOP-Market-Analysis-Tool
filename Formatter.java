import java.util.ArrayList;
import java.util.Date;

public class Formatter {

    public Formatter(String ticker, String startDate, String endDate, double highestClosingPrice, double lowestClosingPrice, double averageClosingPrice, double priceChange, double averageTradingVolume, double bestTradingDay, double worstTradingDay) {

        System.out.println("Ticker: " + ticker);
        System.out.println("Period: " + startDate + " - " + endDate);

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
    public static void DisplayWatchList(
            ArrayList<String> stocks) {

        if (stocks.isEmpty()) {
            System.out.println("\nWatchlist is empty.");
            return;
        }

        System.out.println("\nMY WATCHLIST\n");

        System.out.printf("%-10s %-25s %-15s%n", "Ticker", "Latest Available Price", "Change");

        System.out.println("----------------------------------------------------------");

        for (String stock : stocks) {

            String startDate = ReadFile.getEarliestDate(stock);

            String endDate = ReadFile.getLatestDate(stock);

            ArrayList<PriceRecord> data = ReadFile.read(stock, startDate, endDate);

            double latestPrice = Calculations.latestClose(data);

            double change = Calculations.percentChange(data);

            System.out.printf("%-10s $%-24.2f %+10.2f%%%n", stock, latestPrice, change);
        }
    }

    public static void CompareTickers(
            ArrayList<String> stocks) {

        if (stocks.size() < 2) {

            System.out.println("Please select at least two stocks.");

            return;
        }

        System.out.printf("%-20s", "");

        for (String stock : stocks) {

            System.out.printf("%-15s", stock);
        }

        System.out.println();

        System.out.println("--------------------------------------------------------------------------");

        System.out.printf("%-20s", "Period Return");

        for (String stock : stocks) {

            ArrayList<PriceRecord> data = ReadFile.read(stock, ReadFile.getEarliestDate(stock), ReadFile.getLatestDate(stock));

            System.out.printf( "%-15.2f", Calculations.percentChange(data));
        }

        System.out.println("%");

        System.out.printf("%-20s", "Average Price");

        for (String stock : stocks) {

            ArrayList<PriceRecord> data = ReadFile.read(stock, ReadFile.getEarliestDate(stock), ReadFile.getLatestDate(stock));

            System.out.printf("%-15.2f", Calculations.averageClose(data));
        }

        System.out.println();

        System.out.printf("%-20s", "Highest Price");

        for (String stock : stocks) {

            ArrayList<PriceRecord> data = ReadFile.read(stock, ReadFile.getEarliestDate(stock), ReadFile.getLatestDate(stock));

            System.out.printf("%-15.2f", Calculations.highestClose(data));
        }

        System.out.println();

        System.out.printf("%-20s", "Lowest Price");

        for (String stock : stocks) {

            ArrayList<PriceRecord> data = ReadFile.read(stock, ReadFile.getEarliestDate(stock), ReadFile.getLatestDate(stock));

            System.out.printf("%-15.2f", Calculations.lowestClose(data));
        }

        System.out.println();

        System.out.printf("%-20s", "Average Volume");

        for (String stock : stocks) {

            ArrayList<PriceRecord> data = ReadFile.read(stock, ReadFile.getEarliestDate(stock), ReadFile.getLatestDate(stock));

            System.out.printf("%-15.0f", Calculations.averageTradingVolume(data));
        }

        System.out.println();
    }
    public static void displayFilteredData(ArrayList<PriceRecord> filteredData) {

        if(filteredData.isEmpty()) {

            System.out.print("No data found");

            return;
        }

        System.out.printf("%-12s %-15s %-15s%n", "Date", "Close Price", "Volume");

        System.out.println("---------------------------------------------");

        for(PriceRecord record : filteredData) {

            System.out.printf("%-12s %-15.2f %-15d%n", record.getDate(), record.getClose(), record.getVolume());
        }
    }
}
