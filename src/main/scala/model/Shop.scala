package demo.model

final case class Shop(
                 id: Option[Int] = None,
                 name: String,
                 businessName: Option[String],
                 activityId: Int,
                 stratumId: Int,
                 address: String,
                 phoneNumber: Option[String],
                 email: Option[String],
                 website: Option[String],
                 shopTypeId: Int,
                 lat: Double,
                 lng: Double
               )
