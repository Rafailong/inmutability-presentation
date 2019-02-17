package me.rafaavila.immutability

import monocle.Lens
import monocle.macros.GenLens
import io.circe.generic.auto._, io.circe.syntax._

object Lenses extends App with MyRenderer {

  // creating our nested data!
  val address = Address(10, "Americas Av")
  val gym = Gym("SmartFit", address)
  val member = Member("Gil", "Luna", gym)

  // d' you remember the copy block from ImmutableObject example?
  // well, we have tool called: optics/lenses
  // with optics we can focus on (view) a value on a nested data structure
  // and we can work over (edit/delete/renew) the value we focus on.
  // so, to focus on a value in nested data structure we need:
  // 1. a path-knower (optic), and
  // 2. a hole (our model or nested data structure)
  // for this example member is our hole.
  // So, let's explore, Dora!

  // good-old copy
  val newMe = member.copy(
    gym = member.gym.copy(
      address = member.gym.address.copy(
        street = "2nd St"
      )
    )
  )

  // optics
  // First path-knowers
  val memberGym: Lens[Member, Gym] = GenLens[Member](_.gym)
  val gymAddress: Lens[Gym, Address] = GenLens[Gym](_.address)
  val addressStreet: Lens[Address, String] = GenLens[Address](_.street)

  val pathKnower  = memberGym composeLens gymAddress composeLens addressStreet

  val upperMemberGymAddress = pathKnower.modify(_.toUpperCase) //composed optic

  println(
    upperMemberGymAddress(member).asJson.toString() // applying optics
  )

  // ok, now we have a bunch of optics? YES, but we can compose them
  // and we are being more explicit in our focusing and modifications
  // on the nested data structure.
}
