package examples

import scala.collection.mutable.ListBuffer
import scala.util.control.TailCalls.{TailRec, done, tailcall}

object isEven_tc {
  def main(args: Array[String]): Unit = {
    val num = args(0).toInt
    val repetitions = args(1).toInt
    val times = ListBuffer[Long]()

    for(_ <- 1 to repetitions){
      val startTimeNanos = System.nanoTime()
      even(num).result
      val endTimeNanos = System.nanoTime()
      val duration = endTimeNanos - startTimeNanos
      println(f"TailCall Duration: $duration ns\n")
      times+=duration
    }
    println(f"Average TailCall Duration over $repetitions repetitions: ${(times.sum/repetitions)/1000000} ms")
  }

  def even(i: Int): TailRec[Boolean] = i match {
    case 0 => done(true)
    case _ => tailcall(odd(i - 1))
  }

  def odd(i: Int): TailRec[Boolean] = i match {
    case 0 => done(false)
    case _ => tailcall(even(i - 1))
  }
}


