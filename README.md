# CSV-Data-Manipulation-Spring-Boot

Project name: CSVDataManipulation

Description:

The customer has just informed us that we need to churn out a small application ASAP to support a new project. Here is what they need:

1. We need a Java application that will consume CSV file, parse the data and insert valid record into a SQLite database.
a. Database name: <input-filename>.db
b. It should have 1 table with 10 columns A, B, C, D, E, F, G, H, I, J that corresponds to the CSV column header names

2. Each record needs to be verified to contain the right number of data elements to match the columns.
a. Records that do not match the column count must be written to <input-filename>-bad.csv

3. At the end of processing, write statistics to a log file: <input-filename>.log
a. # of records received
b. # of records successful
c. # of records failed

4. Data sets can be extremely large, so be sure the processing is optimized.

5. Application should be re-runnable, meaning that multiple runs with same input produce the same results.

Summary:

The developer created a spring boot application that consume a csv file then save the valid records to MySQL database. The table has 11 columns including the given headers and an additional unique identifier. The invalid records and statistics are written in a csv and log file, respectively. The data sets are handled using the hibernate properties. Lastly, the application has no multithreading implementation.

Steps to Replicate:

1. Clone the repository and import the project in eclipse.
2. Create ms3Interview schema in MySQL.
3. Configure the application properties for the username and password of the database connection.
4. Run the project as spring boot application.

Approach:

The developer created a method to check each record that contains null/empty column. This method has been use to separate the valid and invalid records.



