package me.rafaavila.immutability

import cats.effect._
import io.circe.generic.auto._, io.circe.syntax._

object ImmutableObjects extends App{

  val address = Address(1, "1st St")
  val gym1 = Gym("Arnold", address)

  // suppose we only have
  val me = Member("Rafael", "Avila", gym1)

  // we want to update the address of its gym
  val newMe = me.copy(
    gym = me.gym.copy(
      address = me.gym.address.copy(
        street = "2nd St"
      )
    )
  )

  println(me.asJson.toString(), newMe.asJson.toString())

  // NOW, this was easy but, what if the data data we are working with
  // is pretty nested? Just Imagine how deep the copy expression will be? :O
  // like http://opensource.adobe.com/Spry/samples/data_region/JSONDataSetSample.html
}

object ImmutableObjects2 extends App {

  // we do not only have objects as immutable values
  // we also have our programs as immutable values
  // values which express the program per-se

  def program() = IO {
    println("What up, dude?")
  }

  program() // what will be sent to the console?

  // explain and complete!
}
