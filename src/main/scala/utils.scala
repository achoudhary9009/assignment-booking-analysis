import models._
import org.apache.spark.sql.Encoders

object utils {

  def registerEncoders(): Unit = {
    Encoders.product[Booking]
    Encoders.product[Event]
    Encoders.product[DataElement]
    Encoders.product[TravelRecord]
    Encoders.product[Passenger]
    Encoders.product[Product]
    Encoders.product[Flight]
    Encoders.product[Airport]
  }


}
