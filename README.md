# tailcallspp

`isEven_tcpp.scala` uses the optimised `TailCalls` library. To test, use: `sbt "project examples" "runMain examples.isEven_tcpp $num $repetitions"` where `$num` is the number to check whether it is even (e.g. 1000000) and `$repetitions` is the number of repetitions to run the experiment (e.g. 30). The average should be given in milliseconds once it terminates. 

Similarly, use `sbt "project examples" "runMain examples.isEven_tc $num $repetitions"` to test the same mutual recursive function which uses the standard library without any optimisations. 