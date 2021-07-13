package examples

import library.TailCallspp

import scala.annotation.tailrec
import scala.util.control.TailCalls.{TailRec, done, tailcall}

object isEven {
  def main(args: Array[String]): Unit = {
    val num = args(0).toInt
    var startTimeMillis: Long  = 0
    var endTimeMillis:Long = 0
    var duration: Long = 0
    startTimeMillis = System.currentTimeMillis()
//    println(tcppeven(num).result)
    endTimeMillis = System.currentTimeMillis()
    duration = (endTimeMillis - startTimeMillis)
    println(f"Count+Max TailCallPP Duration: $duration ms\n")

    startTimeMillis = System.currentTimeMillis()
//    println(tcppeven(num).result)
    endTimeMillis = System.currentTimeMillis()
    duration = (endTimeMillis - startTimeMillis)
    println(f"Count+Max TailCallPP Duration: $duration ms\n")

    startTimeMillis = System.currentTimeMillis()
    println(tceven(num).result)
    endTimeMillis = System.currentTimeMillis()
    duration = (endTimeMillis - startTimeMillis)
    println(f"Vanilla TailCall Duration: $duration ms\n")

    startTimeMillis = System.currentTimeMillis()
    println(tcCMeven(num).result)
    endTimeMillis = System.currentTimeMillis()
    duration = (endTimeMillis - startTimeMillis)
    println(f"Count+Max TailCall Duration: $duration ms\n")

    startTimeMillis = System.currentTimeMillis()
    println(tcHeven(num).result)
    endTimeMillis = System.currentTimeMillis()
    duration = (endTimeMillis - startTimeMillis)
    println(f"Stack Frame Heuristic TailCall Duration: $duration ms")
  }

  def tceven(i: Int): TailRec[Boolean] = i match {
    case 0 => done(true)
    case _ => tailcall(tcodd(i - 1))
  }

  def tcodd(i: Int): TailRec[Boolean] = i match {
    case 0 => done(false)
    case _ => tailcall(tceven(i - 1))
  }

  val max = 1000
  var count = 0

  def tcCMeven(i: Int): TailRec[Boolean] = i match {
    case 0 => done(true)
    case _ =>
      if(count<max) {
        count+=1
        tcCModd(i - 1)
      } else {
        count=0
        tailcall(tcCModd(i - 1))
      }
  }

  def tcCModd(i: Int): TailRec[Boolean] = i match {
    case 0 => done(false)
    case _ =>
      if(count<max) {
        count+=1
        tcCMeven(i - 1)
      } else {
        count=0
        tailcall(tcCMeven(i - 1))
      }
  }

  var countH = 0
  def tcHeven(i: Int): TailRec[Boolean] = i match {
    case 0 => done(true)
    case _ =>
      countH += 1
      val rt = Runtime.getRuntime
      val freemem = rt.freeMemory
      val totalmem = rt.totalMemory
      val sizeperframe = totalmem/countH

      if(sizeperframe < freemem){
        tcHodd(i - 1)
      } else {
        countH=0
        tailcall(tcHodd(i - 1))
      }
  }

  def tcHodd(i: Int): TailRec[Boolean] = i match {
    case 0 => done(false)
    case _ =>
      countH += 1
      val rt = Runtime.getRuntime
      val freemem = rt.freeMemory
      val totalmem = rt.totalMemory
      val sizeperframe = totalmem/countH

      if(sizeperframe < freemem){
        tcHeven(i - 1)
      } else {
        countH=0
        tailcall(tcHeven(i - 1))
      }
  }

//  def tcppeven(i: Int): TailCallspp.TailRec[Boolean] = i match {
//    case 0 => TailCallspp.done(true)
//    case _ => tailcallpp(tcppodd(i-1))
//  }
//
//  def tcppodd(i: Int): TailCallspp.TailRec[Boolean] = i match {
//    case 0 => TailCallspp.done(false)
//    case _ => tailcallpp(tcppeven(i - 1))
//  }

//  def heven(i: Int): Control[Boolean] = i match {
//    case 0 => Done(true)
//    case _ => htailcallpp(hodd(i-1))
//  }
//
//  def hodd(i: Int): Control[Boolean] = i match {
//    case 0 => Done(false)
//    case _ => htailcallpp(heven(i - 1))
//  }

}

