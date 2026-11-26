import java.util.ArrayList;

public class Filter {
      public static ArrayList<PriceRecord>  filterByVolume(ArrayList<PriceRecord> data, long minimumVolume){

          ArrayList<PriceRecord> filteredData = new ArrayList<>();

        for(PriceRecord record : data){

            if(record.getVolume()>=minimumVolume){

                filteredData.add(record);
            }
        }
        return  filteredData;
    }
    public static ArrayList<PriceRecord>  filterByPrice(ArrayList<PriceRecord> data, double minimumPrice) {
        ArrayList<PriceRecord> filteredData = new ArrayList<>();

            for (PriceRecord record : data) {

                if (record.getClose() >= minimumPrice) {

                    filteredData.add(record);
                }
            }
           return filteredData;
        }
}
