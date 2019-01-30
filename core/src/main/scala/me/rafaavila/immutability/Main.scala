package me.rafaavila.immutability

import reftree.core._
import reftree.diagram.Diagram

object Main extends App with MyRenderer {
  import renderer._

  case class Person(firstName: String, age: Int)
  Diagram.sourceCodeCaption(Person("Bob", 42)).render("example")

  case class Tree(size: Int, value: Int, children: List[Tree])
  implicit def treeInstance: ToRefTree[Tree] = ToRefTree[Tree] { tree: Tree =>
    RefTree.Ref(tree, Seq(
      RefTree.Val.formatted(tree.size)(_.toHexString).toField.withName("s"),

      // highlight the value
      tree.value.refTree.withHighlight(true).toField.withName("value"),
      // do not label the children
      tree.children.refTree.toField
    )).rename("MyTree") // change the name displayed for the class
  }
  Diagram.sourceCodeCaption(Tree(0, 0, Nil)).render("my-tree")
}