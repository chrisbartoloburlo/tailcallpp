package examples

import library.{Cont, Control, Done}
import library.tailcallpp.tailcallpp

import scala.util.control.TailCalls.{TailRec, done, tailcall}

object isEven {
  def main(args: Array[String]): Unit = {
    print(even(args(0).toInt))
  }

  val max = 300;
  var count = 0;

  def even(i: Int): Control[Boolean] = i match {
    case 0 => Done(true)
    case _ => println(i); tailcallpp(odd(i - 1))
    //    case _ =>
    //      if (max < count){
    //        count += 1
    //        odd(i - 1)
    //      } else {
    //        count = 0
    //        tailcall(odd(i-1))
    //      }
    //    case _ => println(i); odd(i - 1)
  }

  def odd(i: Int): Control[Boolean] = i match {
    case 0 => Done(false)
    case _ => println(i); tailcallpp(even(i - 1))
    //    case _ =>
    //      if (max < count){
    //        count += 1
    //        even(i - 1)
    //      } else {
    //        count = 0
    //        tailcall(even(i-1))
    //      }
    //    case _ => println(i); even(i - 1)
  }
}

