package examples

import library.TailCallspp.{TailRec, done, setMax, tailcall}

import scala.collection.mutable.ListBuffer

object isEven_tcpp {
  def main(args: Array[String]): Unit = {
    val num = args(0).toInt
    val repetitions = args(1).toInt
    val times = ListBuffer[Long]()

    setMax(1000)

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
    case _ => tailcall(odd(i - 1))
  }

  def odd(i: Int): TailRec[Boolean] = i match {
    case 0 => done(false)
    case _ => tailcall(even(i - 1))
  }
}
