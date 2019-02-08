package me.rafaavila.immutability

import scala.annotation.tailrec

object FingMe extends App {

  /*
  In this part of the talk we are going to:

  1. Define basic functions
  2. Check function's properties
  3. Use higher-order functions

  So... let's get started!
  */

  object one {

    // good function!
    val add = (a: Int, b: Int) => a + b

    // what's wrong with this function?
    val divide = (a: Int, b: Int) => a / b
  }

  object two {

    /*
    data structures to the rescue!
    NOTE: Errors/Exceptions are also known as side-effects!
     */
    val divide = (a: Int, b: Int) => b match {
      case x if b <= 0 => None
      case _ => Some(a / b)
    }
  }

  object three {

    def sum(list: List[Int]): Int = {

      @tailrec
      def iterate(list: List[Int], acc: Int): Int = list match {
        case Nil   => acc
        case n::ns => iterate(ns, acc + n)
      }

      iterate(list, 0)
    }

    def length[A](list: List[A]): Int = {

      @tailrec
      def iterate(list: List[A], acc: Int): Int = list match {
        case Nil   => acc
        case _::ns => iterate(ns, acc + 1)
      }

      iterate(list, 0)
    }

    def map[A, B](f: A => B)(list: List[A]): List[B] = {

      @tailrec
      def iterate(list: List[A], acc: List[B]): List[B] = list match {
        case Nil   => acc
        case n::ns => iterate(ns, f(n)::acc)
      }

      iterate(list, List.empty[B]).reverse
    }

    def fold[A](f: (A, A) => A)(acc: A)(list: List[A]): A = {

      @tailrec
      def iterate(list: List[A], acc: A): A = list match {
        case Nil   => acc
        case n::ns => iterate(ns, f(acc, n))
      }

      iterate(list, acc)
    }

    def filter[A](f: A => Boolean)(list: List[A]): List[A] = {

      @tailrec
      def iterate(list: List[A], acc: List[A]): List[A] = list match {
        case Nil   => acc
        case n::ns => iterate(ns, if(f(n)) n::acc else acc)
      }

      iterate(list, List.empty[A]).reverse

    }

    def sum2(list: List[Int]): Int =
      fold[Int]((a: Int, b: Int) => a + b)(0)(list)
  }
}
