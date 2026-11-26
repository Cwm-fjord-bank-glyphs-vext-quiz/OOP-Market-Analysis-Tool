import java.util.InputMismatchException;
import java.util.Scanner;

public class Validation {

    public static String  getValidTicker(Scanner scanner) {

        while(true) {

        System.out.println("Enter Ticker");

        String ticker = scanner.nextLine();

        if(ReadFile.tickerExists(ticker)) {

            return ticker;
            }

            System.out.println("Ticker Does Not Exist");
        }
    }
    public static int getValidInt(Scanner scanner) {

        while (true) {

            try{

                int value = scanner.nextInt();

                scanner.nextLine();

                return value;

            }catch (InputMismatchException e){

                System.out.println("Enter a valid integer");

                scanner.nextLine();
            }
        }
    }
    public static double getValidDouble(Scanner scanner) {

        while (true) {

            try{

                double value = scanner.nextDouble();

                scanner.nextLine();

                return value;

            }catch (InputMismatchException e){

                System.out.println("Enter a valid Double");

                scanner.nextLine();
            }
        }
    }
    public static long getValidLong(Scanner scanner) {

        while (true) {

            try{

                long value = scanner.nextLong();

                scanner.nextLine();

                return value;

            }catch (InputMismatchException e){

                System.out.println("Enter a valid long");

                scanner.nextLine();
            }
        }
    }
    public static String validDate(Scanner scanner) {

        while(true) {

            String date = scanner.nextLine();

            if (date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                return date;

                }else{

                System.out.println("Error: Dates must be in YYYY-MM-DD format.");
            }
        }
}  }
