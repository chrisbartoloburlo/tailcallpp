package library

import scala.annotation.tailrec
import scala.util.control.TailCalls.{TailRec, tailcall}

sealed trait Control[A]
case class Done[A](res: A) extends Control[A]
case class Cont[A](call: () => A) extends Control[A]
case class ContClean[A](call: () => Control[A]) extends Control[A]

object tailcallpp {
  val max = 300;
  var count = 0;

  def tailcallpp[A](f: => A): Control[A] = {
    if (count < max) {
      count += 1
      println("count", count)
      Done(f)
    } else {
      println("cleaning", Runtime.getRuntime.freeMemory)
      count=0
      Cont[A](() => f)
    }
  }

//  def tailcallpp[A](f: => A): A = {
//    print("test")
//    if (count < max) {
//      count += 1
//      println("count", count)
//      f
//    } else {
//      println("cleaning", Runtime.getRuntime.freeMemory)
//      count=0
//      f
//    }
//  }
}
