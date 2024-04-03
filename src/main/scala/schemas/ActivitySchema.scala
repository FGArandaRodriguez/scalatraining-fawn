package demo.schema

import cats.effect.Async
import sangria.schema.{ fields, Field, IntType, ObjectType, StringType }
//Importamos los modelos que usaremos
import demo.model.Activity
import demo.model.MainRepository

object ActivitySchema {

  def apply[F[_]: Async]: ObjectType[MasterRepo[F], Activity] =
    ObjectType(
      name = "Activity",
      fieldsFn = () =>
        fields(
          Field(
            name        = "id",
            fieldType   = IntType,
            description = Some("Activity id."),
            resolve     = _.value.id
          ),
          Field(
            name        = "name",
            fieldType   = StringType,
            description = Some("Activity name."),
            resolve     = _.value.name
          )
        )
    )
}