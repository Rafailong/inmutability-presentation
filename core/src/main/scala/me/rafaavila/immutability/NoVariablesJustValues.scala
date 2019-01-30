package me.rafaavila.immutability

import java.util.UUID

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object NoVariablesJustValues extends App with MyRenderer {

  private def sayIt(str: String) = str ++ " " ++ "awesome!"

  // in every modern programming language the primitive types
  // are immutable
  println(1 + 3) // we'll get 4 and 1 will still be 1, the same will happen with 3

  // classic example is String type
  // the String's are immutable, so we can
  var mx = "mexico"
  println(sayIt(mx))

  // and mx will still be "mexico"
  println(s"mx = [$mx]")

  // DANGER! mutation coming!
  // but mx is a VARiable, what if we change it?
  mx = sayIt(mx)
  println(s"mx now is [$mx]")

  // some language like, Scala, offer both
  // VALues and VARiables
  // Think a VALues as a box that we can set a thing in it just once
  // Think a VARiable as a box we can set a thing in it whenever we want
  val col = "Colombia"
  // col = sayIt(col) // does not compile
  println(sayIt(col)) // we can apply the same ops to VALues but mutate them

  // Also we have mutable types like StringBuffer
  // let see how we can get hurt by such a type
  // we need concurrent operations here so...

  // simulates getting FacebookID by user email
  def fetchFacebookID(email: String): Future[String] = Future {
    Thread.sleep(50)
    s"FBID: ${UUID.randomUUID().toString}"
  }

  // simulates getting address by user email
  def fetchUserAddress(email: String): Future[String] = Future {
    Thread.sleep(100)
    s"Americas Av"
  }

  val email = "user@mail.com"
  val sb = new StringBuilder("[email:" ++ email ++ "]")
  println(sb.mkString)

  // as business requirement we need to get out a string from sb like:
  // [email:XXXXXXXXX][address:XXXXXXXXX][FacebookId:XXXXXXXXXX]
  fetchUserAddress(email).map(address => sb.append(s"[address:$address]"))
  fetchFacebookID(email).map(id => sb.append(s"[FacebookId:$id]"))
  Thread.sleep(200)
  println(sb.mkString)
}
