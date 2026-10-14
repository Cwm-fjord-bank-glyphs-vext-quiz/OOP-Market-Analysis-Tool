# OOP-Market-Analysis-Tool
## Description on how to use project
The project analyzes the stock market data collected in CSV files. The program outputs various metrics, relevant to the date range selected.

Based on the Market Data folder attached, pick any of the ticker names on the files. Input the ticker name following the same syntax as on the file. Then specify a date range using the dates in the file, make sure to follow a YYYY-MM-DD format. The program is design to check for a correct date format, the existence of the data, that the start date is before the end date. 

Finally, the Market Assistant will output the different metrics. These include highest closing price, lowest closing price, average closing price, average closing price, percentage change over selected period, average trading volume, best trading day (% increase), worst trading day (% decrease).

## Example output: 
Ticker: GOOGL
Period: 2025-01-02 - 2025-02-28

Highest Closing Price: 206.38
Lowest Closing Price: 168.50
Average Closing Price: 189.97
Price Change: -10.11%
Average Trading Volume: 28,970,262

Best Trading Day: 3.11%
Worst Trading Day: -7.29%

## Project Structure:
### Main.java
Controls the user interactions of the program, and interactions between different methods
### ReadFile.java
Loads the CSV file, reads the data, and filters out the data storing it in an ArrayList
### cleanUp.java
Receives Array List from ReadFile.java, and organizes the data into specific columns based on data type
### calculations.java
Preforms calculations using the cleaned-up data received from CleanUp.java 
### Formater.java
Formats the data clearly recived from calculations.java, and outputs the relevant information 

## Diagram

## Development Efforts
### Sandra
Responsible for development of calculations.java, and Formatter.java, created the UML diagram. Furthermore, contributed to debugging and testing, and assisted with implementing error handling. 
### Xavier
Responsible for development of Main.java, ReadFile.java, and cleanUp.java, created README.md and contributed to the final testing before commitment. 
