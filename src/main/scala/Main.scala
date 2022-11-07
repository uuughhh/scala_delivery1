object Main extends App {
  
  new task1
  
}

// Task 1 A-D
class task1 {

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

