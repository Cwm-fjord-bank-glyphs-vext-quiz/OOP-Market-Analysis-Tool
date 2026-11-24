import java.util.ArrayList;

public class CleanUp {
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

