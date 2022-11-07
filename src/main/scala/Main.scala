object Main extends App {

  new task2BC
  
}

// Task2 (b)(c)
class task2BC extends Thread {

  private var counter: Int = 0

  def increaseCounter(): Unit = this.synchronized {
    counter += 1
  }

  def printCounter():Unit = {
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
  val t1 = thread{increaseCounter()}
  val t2 = thread{increaseCounter()}
  val t3 = thread{printCounter()}
}