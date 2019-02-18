package me.rafaavila.immutability

import reftree.core._
import reftree.diagram._
import reftree.render._
import scala.collection.immutable._
import java.nio.file.Paths
import Diagram.{sourceCodeCaption ⇒ diagram}

// http://stanch.github.io/reftree/talks/Immutability.html
object ImmutableDataStructures extends App with MyRenderer {

  import renderer._

  Animation
    .startWith(List(1))
    .iterate(_ :+ 2, _ :+ 3, _ :+ 4)
    .build().render("list-append", tweakAnimation = _.withOnionSkinLayers(3))

  Animation
    .startWith(List(1))
    .iterate(2 :: _, 3 :: _, 4 :: _)
    .build().render("list-prepend", tweakAnimation = _.withOnionSkinLayers(3))


  val queue1 = Queue(1, 2, 3)
  val queue2 = (queue1 :+ 4).tail
  (diagram(queue1) + diagram(queue2)).render("queues", _.withVerticalSpacing(1.2))

  Animation
    .startWith(Queue(1, 2, 3))
    .repeat(3)(_.iterate(2)(q ⇒ q :+ (q.max + 1)).iterate(2)(_.tail))
    .build(Diagram.toStringCaption(_).withAnchor("queue"))
    .render("queue")
}
