fun main() {
    /**
     * You can pass a variable number of argumenrs to a function by declaring the function with a vararg parameter
     * Consider the following sumOfNumbers() function which accepts vararg of numbers
     */

    //You can call the below function with any number of arguments
    sumOfNumbers(1.5, 2.0, 5.0)

    sumOfNumbers(1.5, 2.0, 3.5, 4.0, 5.8, 6.2)

    sumOfNumbers(1.5, 2.0, 3.5, 4.0, 5.8, 6.2, 8.1, 12.4, 16.5)

    /**
     * In Kotlin, a vararg parameter of type T is internally represented as an array of type T (Array<T>) inside the function body.
     * A function may have only one vararg parameter. If there are other parameters following the vararg parameter,
     * then the values for those parameters can be passed using the named argument syntax -
     */

    sumOfNumbersWithInitalValue(1.5, 2.5, intialValue = 100.0)

    /**
     * The named argument is mandatory , if we need it to be optional then we need to give a default value
     */
    sumOfNumbersWithInitalValue(1.5, 2.5, 100.0)


    /**
     * SPREAD OPERATOR
     * Usually, we pass the arguments to a vararg function one-by-one.
     * But if you already have an array and want to pass the elements of the array to the vararg function, then you can use the spread operator like this -
     */

    val a = doubleArrayOf(1.5, 2.6, 5.4)
    sumOfNumbers(*a)

}

fun sumOfNumbers(vararg numbers: Double) {
    var sum: Double = 0.0
    for (number in numbers) {
        sum += number
    }
    println("sumOfNumbers is $sum")
}

fun sumOfNumbersWithInitalValue(vararg numbers: Double, intialValue: Double = 0.0) {
    var sum: Double = intialValue
    for (number in numbers) {
        sum += number
    }
    println("sumOfNumbersWithInitalValue is $sum")

}



