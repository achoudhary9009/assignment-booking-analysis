import models.{Airport, Booking}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.{Dataset, SparkSession}

object MySparkApp {
  def main(args: Array[String]): Unit = {

    val master = args(0)
    val bookingsPath = args(1) // Path to the bookings JSON file
    val airportsPath = args(2) // Path to the airports CSV file
    val startDate = args(3) // Start date for the time range
    val endDate = args(4) // End date for the time range

    val spark = SparkSession.builder()
      .appName("My Spark Application")
      .master(master)
      .getOrCreate()

    import spark.implicits._


    // Load bookings data
    val bookings: Dataset[Booking] = spark.read.json(bookingsPath).as[Booking]

    // Load airports data
    val airports: Dataset[Airport] = spark.read
      .option("header", "false")
      .option("inferSchema", "true")
      .option("delimiter", ",")
      .schema(Airport.schema)
      .csv(airportsPath)
      .as[Airport]


    // Filter bookings within the specified time range
    val filteredBookings = bookings.filter(bookings("timestamp").between(startDate, endDate))
    val klmFlights = filteredBookings.where(array_contains(col("event.DataElement.travelrecord.productsList.flight.marketingAirline"),"KL"))
    val airportsNl = airports.filter(airports("country") === "Netherlands")
    val originNlFlights = airportsNl.join(klmFlights.alias("booking"), expr("array_contains(event.DataElement.travelrecord.productsList.flight.originAirport, iataCode)")).select("booking.*")
    val FlightsWithDestination = airports.join(originNlFlights, expr("array_contains(event.DataElement.travelrecord.productsList.flight.destinationAirport, iataCode)"))
    val FlightsOutOfNl = FlightsWithDestination.filter(FlightsWithDestination("country") =!= "Netherlands")


    // Group by country, day of the week, and season
    val result = FlightsOutOfNl.groupBy(
      $"country",
      date_format($"timestamp", "EEEE").alias("dayOfWeek"),
      when(month($"timestamp").between(3, 5), "Spring")
        .when(month($"timestamp").between(6, 8), "Summer")
        .when(month($"timestamp").between(9, 11), "Autumn")
        .otherwise("Winter")
        .alias("season")
    ).agg(countDistinct(when(array_contains($"event.DataElement.travelrecord.productsList.bookingStatus", "CONFIRMED"), $"event.DataElement.travelrecord.passengersList.uci")).as("passengerCount"))
      .orderBy(desc("passengerCount"), $"season", $"dayOfWeek")

    result.show()

    spark.stop()
  }

}
