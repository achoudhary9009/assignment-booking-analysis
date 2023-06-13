package models

import org.apache.spark.sql.types._

case class Airport(airportId: Int, name: String, city: String, country: String, iataCode: String, icaoCode: String,
                   latitude: Double, longitude: Double, altitude: Int, timezone: Double, dst: String, tz: String,
                   airportType: String, source: String)


object Airport{

  val schema = StructType(Seq(
    StructField("AirportID", IntegerType, nullable = false),
    StructField("Name", StringType, nullable = false),
    StructField("City", StringType, nullable = false),
    StructField("Country", StringType, nullable = false),
    StructField("iataCode", StringType, nullable = true),
    StructField("icaoCode", StringType, nullable = true),
    StructField("Latitude", DoubleType, nullable = false),
    StructField("Longitude", DoubleType, nullable = false),
    StructField("Altitude", IntegerType, nullable = false),
    StructField("Timezone", DoubleType, nullable = false),
    StructField("DST", StringType, nullable = false),
    StructField("tz", StringType, nullable = false),
    StructField("airportType", StringType, nullable = false),
    StructField("Source", StringType, nullable = false)
  ))

}