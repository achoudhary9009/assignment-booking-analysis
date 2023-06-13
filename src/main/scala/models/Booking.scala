package models

case class Booking(timestamp: String, event: Event)

case class Event(DataElement: DataElement)

case class DataElement(travelrecord: TravelRecord)

case class TravelRecord(creationDate: String, purgeDateAmd: String, lastEotDate: String, envelopNumber: Long,
                        nbPassengers: Long, isMarketingBlockspace: Boolean, isTechnicalLastUpdater: Boolean,
                        attributeType: String, passengersList: Array[Passenger], productsList: Array[Product])

case class Passenger(age: Long, uci: String, passengerType: String, tattoo: Long, weight: Long, category: String)

case class Product(`type`: String, tattoo: String, bookingStatus: String, bookingClass: String,
                   transportClass: String, aircraftType: String, nbPassengers: String, yieldOrigin: String,
                   yieldDestination: String, yieldTripOrigin: String, yieldTripDestination: String,
                   yieldPointOfCommencement: String, flight: Flight, journeyInTattoo: Option[String] = None,
                   journeyOutTattoo: Option[String] = None)

case class Flight(marketingAirline: String, marketingFlightNumber: String, originAirport: String,
                  originTerminal: Option[String] = None, destinationAirport: String,
                  destinationTerminal: Option[String] = None, departureDate: String, arrivalDate: String,
                  operatingAirline: String, operatingFlightNumber: String)