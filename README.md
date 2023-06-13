# Commercial Booking Analysis

Provide detailed information on your product and how to run it here


## Requirements

- Apache Spark (version 3.4.0)
- Scala (version 2.12.17)
- Hadoop YARN(for running on a cluster)

## Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/achoudhary9009/assignment-booking-analysis.git
   cd assignment-booking-analysis
## Usage

1. Prepare your input data:

    - Ensure you have the booking data available in JSON format.
    - Place the booking data files in a directory on HDFS. The directory can contain multiple files totaling TBs in size.

2. Run the Spark application:

    - For running on a laptop with a small dataset:

      ```bash
      sbt "run local /path/to/bookings /path/to/airports.csv Start-Date End-Date"
      ```

    - For running on a Hadoop YARN cluster:

      ```bash
      spark-submit --class MySparkApp --master yarn --deploy-mode cluster target/scala-2.12/assignment-booking-analysis.jar hdfs://your-hdfs-input-directory/ /path/to/airports.csv Start-Date End-Date
      ```

3. View the results:

   The application will process the booking data and generate a table showing the number of passengers per country, per day of the week, per season. The results will be printed to the console or saved to an output file, depending on your implementation.
![](../../../Desktop/Screenshot 2023-06-13 at 4.50.15 PM.png)