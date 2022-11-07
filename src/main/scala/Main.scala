import scala.concurrent.Future
import scala.concurrent.Await
import concurrent.ExecutionContext.Implicits.global
import concurrent.duration.DurationInt

object Main extends App {
  new Task2A
  new Task2D
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
