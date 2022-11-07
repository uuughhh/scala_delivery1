import scala.concurrent.Future
import scala.concurrent.Await
import concurrent.ExecutionContext.Implicits.global
import concurrent.duration.DurationInt

object Main extends App {
  new Task1
  new Task2A
  new task2BC
  new Task2D
  
}

// Task 1 A-D
class Task1 {

  // Task 1A
  var array: Array[Int] = Array()
  for (i <- 1 to 50) array :+= i

  // Task 1B: Sum of elements in array using for-loop
  def sum_using_for(array: Array[Int]): Int = {
    var sum: Int = 0
    for (i <- array) sum += i
    sum
  }

  // Task 1C: Sum of elements in array using recursion
  def sum_using_recursion(array: Array[Int]): Int = {
    if (array.isEmpty) 0
    else array.head + sum_using_recursion(array.tail)
  }

  // TASK 1D: n-th Fibonacci number using recursion; where F(0) = 0 and F(1) = 1
  def nth_fibonacci_using_recursion(n: BigInt): BigInt = {
    if (n == 0 || n == 1) n
    else nth_fibonacci_using_recursion(n-1) + nth_fibonacci_using_recursion(n-2)
  }

  /* TASK 1D: What is the differentce between Int and BigInt?
    ANSWER: 
    Int is 32-bit long while BigInt is 64-bit long. 
    Therefore BigInt is used when one is unsure whether integer
    values will exceed the supported range for Int.
  */

  // TESTING:
  println("TASK 1A - The generated array: ")
  println("[" + array.mkString(" ") + "]")
  println("TASK 1B - For Loop: " + sum_using_for(array))
  println("Task 1C - Recursion: " + sum_using_recursion(array))
  println("TASK 1D - n-th Fibonacci number:")
  println("The 10-th Fibonacci number is " + nth_fibonacci_using_recursion(10))
}

class Task2A {
  // Task 2a
  def initializeThread(callback: () => Unit): Thread = {
    return new Thread(new Runnable {
      def run() = {
        callback()
      }
    })
  }
  val thread = initializeThread(() => { println("Hello Scala World") })
  thread.run()
}

// Task2 (b)(c)
class task2BC extends Thread {

  private var counter: Int = 0

  def increaseCounter(): Unit = this.synchronized {
    counter += 1
  }

  def printCounter(): Unit = {
    println(counter)
  }

  // function for threading
  def thread(body: => Unit): Thread = {
    val t = new Thread {
      override def run() = body
    }
    t.start
    t
  }
  // 3 threads
  val t1 = thread { increaseCounter() }
  val t2 = thread { increaseCounter() }
  val t3 = thread { printCounter() }
}

class Task2D {
  // Task 2d
  /** Below is an example code of how a deadlock can occur using lazy val. Keep
    * in mind that the deadlock will sometimes occur in scala version 2, not 3.
    * In version 3, scala improved the initializion of lazy val to reduce the
    * risk of deadlocks. The problem in the code below is that Future
    * initializes object A, and this very instance tries to initialize object B
    * internally. Future also tries to initialize object B which can potentially
    * lead to a deadlock in version 2. According to the scala docs, deadlocks
    * can still occur in version 3 by using recursive lazy vals
    */
  object A {
    lazy val initState = 25
    lazy val start = B.initState
  }

  object B {
    lazy val initState = A.initState
  }

  def runPotentialDeadlock() = {
    val result = Future.sequence(
      Seq(
        Future {
          A.start
        },
        Future {
          B.initState
        }
      )
    )
    Await.result(result, 20.second)
  }

  runPotentialDeadlock()
}
