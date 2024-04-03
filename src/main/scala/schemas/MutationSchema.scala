package demo.schema

import cats.effect.Async
import cats.effect.implicits._
import io.circe.generic.auto._
import sangria.macros.derive._
import sangria.marshalling.circe._
import sangria.schema._
import demo.model.{ Shop }
import demo.repo.MainRepository
import cats.effect.std.Dispatcher

object MutationSchema {

  val InputCreateShop = Argument("input", ShopInput.apply)

  def apply[F[_]: Async](dispatcher: Dispatcher[F]): ObjectType[MasterRepo[F], Unit] =
    ObjectType(
      name = "Mutation",
      fields = fields(
        Field(
          name = "createShop",
          OptionType(IntType),
          arguments = List(InputCreateShop),
          resolve = c => {
            def relationsExists(): Boolean =
              dispatcher.unsafeRunSync(c.ctx.activity.exists((c arg InputCreateShop).activityId)) &&
              dispatcher.unsafeRunSync(c.ctx.stratum.exists((c arg InputCreateShop).stratumId)) &&
              dispatcher.unsafeRunSync(c.ctx.shopType.exists((c arg InputCreateShop).shopTypeId))

            val exists = dispatcher.unsafeRunSync(c.ctx.shop.exists((c arg InputCreateShop).id))
            println("The Shop exist ",exists)
            println(relationsExists())
            if (exists)
              Some((c arg InputCreateShop).id)
            else if (relationsExists()) 
              Some(dispatcher.unsafeRunSync(c.ctx.shop.create(c arg InputCreateShop)))
            else
              Some("null")
          }
        )
      )
    )
}
