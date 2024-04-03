package demo.repo

import cats.effect._
import doobie._

final case class MasterRepo[F[_]](
  activity: ActivityRepo[F],
  shopType: ShopTypeRepo[F],
  stratum: StratumRepo[F],
  shop: ShopRepo[F]
)

object MainRepository {

  def fromTransactor[F[_]: Async](xa: Transactor[F]): MasterRepo[F] =
    MasterRepo(
      ActivityRepo.fromTransactor(xa),
      ShopTypeRepo.fromTransactor(xa),
      StratumRepo.fromTransactor(xa),
      ShopRepo.fromTransactor(xa)
    )
}
