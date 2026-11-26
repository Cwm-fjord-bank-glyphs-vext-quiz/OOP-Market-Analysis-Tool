import java.util.ArrayList;

public class Calculations {
    public static double highestClose(ArrayList<PriceRecord> data) {

        double highestClose = 0;

        for (PriceRecord record : data) {

            double value = record.getClose();

            if (value > highestClose) {
                highestClose = value;
            }
        }

        return highestClose;
    }

    public static double lowestClose(ArrayList<PriceRecord> data) {

        double lowestClose = 10000000;

        for (PriceRecord record : data) {

            double value = record.getClose();

            if (value < lowestClose) {
                lowestClose = value;
            }
        }

        return lowestClose;
    }

    public static double averageClose(ArrayList<PriceRecord> data) {

        double avg = 0;

        for (PriceRecord record : data) {

            avg += record.getClose();
        }

        avg /= data.size();

        return avg;
    }

    public static double latestClose(ArrayList<PriceRecord> data) {
        double latestClose = data.get(data.size() - 1).getClose();

        return latestClose;
    }

    public static double percentChange(ArrayList<PriceRecord> data) {

        double firstClose = data.get(0).getClose();

        double lastClose = data.get(data.size() - 1).getClose();

        double percentChange = ((lastClose - firstClose) / firstClose) * 100;

        return percentChange;
    }

    public static double averageTradingVolume(ArrayList<PriceRecord> data) {

        double avg = 0;

        for (PriceRecord record : data) {

            avg += record.getVolume();
        }

        avg /= data.size();

        return avg;
    }

    public static double percentageIncrease(ArrayList<PriceRecord> data) {

        if (data.size() < 2) {

            return 0;
        }

        double maxIncrease = -999999999;

        for (int i = 1; i < data.size(); i++) {

            double today = data.get(i).getClose();

            double yesterday = data.get(i - 1).getClose();

            double percent = ((today - yesterday) / yesterday) * 100;

            if (percent > maxIncrease) {

                maxIncrease = percent;
            }
        }

        return maxIncrease;
    }

    public static double percentageDecrease(ArrayList<PriceRecord> data) {

        if (data.size() < 2) {
            return 0;
        }

        double maxDecrease = 999999999;

        for (int i = 1; i < data.size(); i++) {

            double today = data.get(i).getClose();

            double yesterday = data.get(i - 1).getClose();

            double percent = ((today - yesterday) / yesterday) * 100;

            if (percent < maxDecrease) {

                maxDecrease = percent;
            }
        }

        return maxDecrease;
    }
}
