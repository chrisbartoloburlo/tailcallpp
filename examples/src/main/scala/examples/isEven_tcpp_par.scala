package examples

import library.TailCallspp.{TailRec, done, setMax, tailcallpp}

import scala.collection.mutable.ListBuffer

object isEven_tcpp_par {
  def main(args: Array[String]): Unit = {
    val num = args(0).toInt
    val repetitions = args(1).toInt
    val times = ListBuffer[Long]()
    for(_ <- 1 to repetitions){
      val startTimeNanos = System.nanoTime()
      even(num).result
      val endTimeNanos = System.nanoTime()
      val duration = endTimeNanos - startTimeNanos
      println(f"TailCallPP Duration: $duration ns\n")
      times+=duration
    }
    println(f"Average TailCallPP Duration over $repetitions repetitions: ${(times.sum/repetitions)/1000000} ms")
  }

  def even(i: Int): TailRec[Boolean] = i match {
    case 0 => done(true)
    case _ => tailcallpp(odd(i - 1), 1000)
  }

  def odd(i: Int): TailRec[Boolean] = i match {
    case 0 => done(false)
    case _ => tailcallpp(even(i - 1), 1000)
  }
}
