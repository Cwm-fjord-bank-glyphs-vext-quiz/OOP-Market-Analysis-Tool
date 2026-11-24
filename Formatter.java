import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Formatter {
    public  Formatter(String Ticker, String StartDate, String EndDate,
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





