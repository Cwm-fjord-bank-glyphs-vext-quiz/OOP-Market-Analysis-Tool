# OOP-Market-Analysis-Tool
## Description on how to use project
The project analyzes the stock market data collected in CSV files. The program outputs various metrics, relevant to the date range selected.

Based on the Market Data folder attached, pick any of the ticker names on the files. Input the ticker name following the same syntax as on the file. Then specify a date range using the dates in the file, make sure to follow a YYYY-MM-DD format. The program is design to check for a correct date format, the existence of the data, that the start date is before the end date. 

Finally, the Market Assistant will output the different metrics. These include highest closing price, lowest closing price, average closing price, average closing price, percentage change over selected period, average trading volume, best trading day (% increase), worst trading day (% decrease).

## Example output: 
![image](https://github.com/Cwm-fjord-bank-glyphs-vext-quiz/OOP-Market-Analysis-Tool/blob/main/Screenshot%202026-10-14%20115611.png)
![image](https://github.com/Cwm-fjord-bank-glyphs-vext-quiz/OOP-Market-Analysis-Tool/blob/main/Screenshot%202026-11-25%20221206.png)
![image](https://github.com/Cwm-fjord-bank-glyphs-vext-quiz/OOP-Market-Analysis-Tool/blob/main/Screenshot%202026-11-25%20221244.png)
![image](https://github.com/Cwm-fjord-bank-glyphs-vext-quiz/OOP-Market-Analysis-Tool/blob/main/Screenshot%202026-11-25%20221312.png)

## Project Structure:
### Main.java
Acts as the entry point of the application and manages the user interaction loop. This class displays the menu, validates user input, processes menu selections, and coordinates the flow of data between the other classes.

**Responsibilities**
- Display menu options
- Accept and validate user input
- Control program execution flow
- Call the appropriate methods from other classes
- Handle program startup and shutdown

### ReadFile.java
Loads stock market data from CSV files and converts each row into a `PriceRecord` object. This class is responsible for reading files, parsing data, and filtering records based on user-specified date ranges.

**Responsibilities**
- Open and read CSV files
- Parse stock market data
- Create `PriceRecord` objects
- Filter data by date range
- Store records in collections for processing

### Filter.java
Provides methods for narrowing large datasets into smaller, more relevant collections. This includes filtering records by stock ticker, date range, or other user-selected criteria.

**Responsibilities**
- Filter records by product/ticker symbol
- Create focused datasets for analysis
- Reduce processing overhead by removing unnecessary records

### Calculations.java
Performs all statistical analysis on stock data. This class calculates performance metrics and identifies significant trends within a selected dataset.

**Responsibilities**
- Calculate percentage changes
- Identify best-performing days
- Identify worst-performing days
- Find minimum and maximum prices
- Generate summary statistics

**Example Calculations**
- Highest closing price
- Lowest closing price
- Greatest daily gain
- Greatest daily loss
- Overall percentage return

### Formatter.java
Handles all console output and presentation of results. Separating formatting logic from business logic makes the program easier to maintain and improves readability.

**Responsibilities**
- Format statistical results
- Print tables and summaries
- Display user-friendly reports
- Control output appearance

## Diagram
<img width="360" height="378" alt="image" src="https://github.com/user-attachments/assets/105db74f-7b8e-4e6e-ba8d-9ef5cf0d4c0f" />


## Development Efforts
### Sandra
Milestone 1: Responsible for development of calculations.java, and Formatter.java, created the UML diagram. Furthermore, contributed to debugging and testing, and assisted with implementing error handling. 

Milestone 2: Responsible for the updating READ.md and creating new UML Class Diagram

### Xavier
Milestone 1: Responsible for development of Main.java, ReadFile.java, and cleanUp.java, created README.md and contributed to the final testing before commitment. 

Milestone 2: Responsible for creating Validation.java, Filter.java and Watchlist.java. Lastly assisted in reworking files to improve cohesion and coupling.
