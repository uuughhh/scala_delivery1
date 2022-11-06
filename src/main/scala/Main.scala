object Main extends App {

  // Task 2a
  def initializeThread(callback: () => Unit): Thread = {
    return new Thread(new Runnable {
      def run() = {
        callback()
      }
    })
  }
  val thread = initializeThread(() => { println("Hello World") })
  // thread.run()
}
