package demo.schema

import demo.repo.MainRepository
import cats.effect.Async
import cats.effect.std.dispatcher
import sangria.schema.{
* 
}

object QuerySchema {
    def fieldsDefinition[F[_]: Async] (dispatcher: Dispatcher[F]): ObjectType[MainRepository[F], Unit] =
        ObjectType(
            name = "Query"
            fields = fields(
                Field(
                    name = "activities",
                    fieldType = ListType(ActivitySchema[F]),
                    resolve = x => dispatcher.unsafeToFuture(x.ctx.activity.fetchAll)
                ),
                Field(

                ),
            )
        )
}

