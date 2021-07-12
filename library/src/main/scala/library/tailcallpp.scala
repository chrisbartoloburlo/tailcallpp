package library

import scala.annotation.tailrec
import scala.util.control.TailCalls.{TailRec, tailcall}

sealed trait Control[A]
case class Done[A](res: A) extends Control[A]
case class Cont[A](call: () => A) extends Control[A]
case class ContClean[A](call: () => Control[A]) extends Control[A]

object tailcallpp {
  val max = 2000
  var count = 0

  def tailcallpp[A](f: => Control[A]): Control[A] = {
    if (count < max) {
      count += 1
      f
    } else {
      count=0
      ContClean(() => f)
    }
  }

  @tailrec
  def run[A](f: => Control[A]): A = {
    f match {
      case Done(res) =>
        res
      case ContClean(call) =>
        run(call.apply())
    }
  }
}

object htailcallpp {
  var count = 0

  def htailcallpp[A](f: => Control[A]): Control[A] = {
    count += 1
    val rt = Runtime.getRuntime
    val freemem = rt.freeMemory
    val totalmem = rt.totalMemory
    val sizeperframe = totalmem/count
//    println("Free Memory", freemem)
//    println("Size per frame", sizeperframe)
//    println("Size per frame * count", sizeperframe*max)
    if(sizeperframe < freemem){
      f
    } else {
      count=0
      ContClean(() => f)
    }
  }

  @tailrec
  def hrun[A](f: => Control[A]): A = {
    f match {
      case Done(res) =>
        res
      case ContClean(call) =>
        hrun(call.apply())
    }
  }
}