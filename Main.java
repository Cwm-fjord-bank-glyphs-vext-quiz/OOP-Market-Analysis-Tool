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

        ArrayList<PriceRecord> data =
                ReadFile.read(ticker, startDate, endDate);

        if (data.isEmpty()) {

            System.out.println("No data found for this ticker or date range.");
            return;
        }

        double highestClosingPrice =
                Calculations.highestClose(data);

        double lowestClosingPrice =
                Calculations.lowestClose(data);

        double averageClosingPrice =
                Calculations.averageClose(data);

        double priceChange =
                Calculations.percentChange(data);

        double averageTradingVolume =
                Calculations.averageTradingVolume(data);

        double bestTradingDay =
                Calculations.percentageIncrease(data);

        double worstTradingDay =
                Calculations.percentageDecrease(data);

        Formatter formatter = new Formatter(
                ticker,
                startDate,
                endDate,
                highestClosingPrice,
                lowestClosingPrice,
                averageClosingPrice,
                priceChange,
                averageTradingVolume,
                bestTradingDay,
                worstTradingDay
        );
    }
}
