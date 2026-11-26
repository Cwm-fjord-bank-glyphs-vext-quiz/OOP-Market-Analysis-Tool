import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Watchlist watchlist = new Watchlist();

        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {

            System.out.println("Menu Options: \n 1. Search Historical Data \n 2. Add Stock to Watchlist \n 3. Remove Stock from Watchlist \n 4. Display all Stocks in Watchlist \n 5. Compare Stocks \n 6.Filter Stocks \n 0. Exit Program");

            int choice = Validation.getValidInt(scanner);

            if (choice == 1) {

                String ticker = Validation.getValidTicker(scanner);

                System.out.println("Enter the starting date range (YYYY-MM-DD)");

                String startDate = Validation.validDate(scanner);

                System.out.println("Enter the ending date range (YYYY-MM-DD)");

                String endDate = Validation.validDate(scanner);

                ArrayList<PriceRecord> data = ReadFile.read(ticker, startDate, endDate);

                if (data.isEmpty()) {

                    System.out.println("No data found for this ticker or date range.");

                    continue;
                }

                double highestClosingPrice = Calculations.highestClose(data);

                double lowestClosingPrice = Calculations.lowestClose(data);

                double averageClosingPrice = Calculations.averageClose(data);

                double priceChange = Calculations.percentChange(data);

                double averageTradingVolume = Calculations.averageTradingVolume(data);

                double bestTradingDay = Calculations.percentageIncrease(data);

                double worstTradingDay = Calculations.percentageDecrease(data);

                Formatter formatter = new Formatter(ticker, startDate, endDate, highestClosingPrice, lowestClosingPrice, averageClosingPrice, priceChange, averageTradingVolume, bestTradingDay, worstTradingDay);
            } else if (choice == 2) {

                String ticker = Validation.getValidTicker(scanner);

                watchlist.addStock(ticker);
            } else if (choice == 3) {

                String ticker = Validation.getValidTicker(scanner);

                watchlist.removeStock(ticker);

            } else if (choice == 4) {

                if(watchlist.isEmpty()){

                    System.out.println("Watchlist is empty");

                }else {
                    watchlist.getStocks();
                }

            } else if (choice == 5) {
                System.out.println("Enter the amount of Stocks you wish to compare.");
                int amount = Validation.getValidInt(scanner);

                ArrayList<String> tickers = new ArrayList<>();

                for (int i = 0; i < amount; i++) {

                    String ticker = Validation.getValidTicker(scanner);

                    tickers.add(ticker);
                }
                Formatter.CompareTickers(tickers);
            } else if (choice == 6) {

                String ticker = Validation.getValidTicker(scanner);

                System.out.println("Enter the Filter Method you wish to compare: \n 1. Price \n 2. Volume");

                int selection = Validation.getValidInt(scanner);

                String Start = ReadFile.getEarliestDate(ticker);

                String End = ReadFile.getLatestDate(ticker);

                if (selection == 1) {

                    System.out.println("Enter a Price to filter by");

                    double price = Validation.getValidDouble(scanner);

                    ArrayList<PriceRecord> Filtered = Filter.filterByPrice(ReadFile.read(ticker, Start, End), price);

                    Formatter.displayFilteredData(Filtered);

                } else if (selection == 2) {

                    System.out.println("Enter a Volume amount to filter by");

                    long volume = Validation.getValidLong(scanner);

                    ArrayList<PriceRecord> Filtered = Filter.filterByVolume(ReadFile.read(ticker, Start, End), volume);

                    Formatter.displayFilteredData(Filtered);
                } else {
                    System.out.println("Error: Invalid selection.");
                }
            } else if (choice == 0) {
                System.out.println("Exiting Program....");

                running = false;

            } else {
                System.out.println("Error: Invalid Menu choice.");
            }
        }
        scanner.close();
    }
}
