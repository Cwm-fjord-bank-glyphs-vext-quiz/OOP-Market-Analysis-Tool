import java.util.ArrayList;

public  class Watchlist {

    private ArrayList<String> stocks;

    public Watchlist() {stocks = new ArrayList<>();}

    public void addStock(String stock) {

        if (!stocks.contains(stock)) {

            stocks.add(stock);

        }else  {

            System.out.println("Error: Stock already exists.");
        }
    }

    public void removeStock(String stock) {stocks.remove(stock);}

    public boolean stockExists(String stock) {
        return  stocks.contains(stock);
    }

    public void getStocks() {Formatter.DisplayWatchList(stocks);}

    public boolean isEmpty() {

        return stocks.isEmpty();

    }
}