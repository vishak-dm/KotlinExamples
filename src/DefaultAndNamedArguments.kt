fun main(args: Array<String>) {


    /** Kotlin supports default arguments in function declarations. You can specify a default value for a function parameter.
     * The default value is used when the corresponding argument is omitted from the function call.
     * Consider the following function for example -
     */

    /**
     * If you call the below function with all the  arguments, it works just like any other function and uses the values passed in the arguments
     */
    sum(4, 5, 6, 7)

    /**
     * However, If you omit the argument that has a default value from the function call, then the default value is used in the function body
     */
    sum(4)

    /**
     * If the function declaration has a default parameter preceding a non-default parameter, then the default value cannot be used while calling the function with position-based arguments.
     * While calling the arithmeticSeriesSum function, you can not omit the argument a from the function call and selectively pass a value for the non-default parameter n
     */
    //arithmeticSeriesSum(10) // error: no value passed for parameter n

    /**
     * When you call a function with position-based arguments, the first argument corresponds to the first parameter, the second argument corresponds to the second parameter, and so on.
     * So for passing a value for the 2nd parameter, you need to specify a value for the first parameter as well -
     */
    arithmeticSeriesSum(1, 10)


    /**
     * However, The above use-case of selectively passing a value for a parameter is solved by another feature of Kotlin called Named Arguments.
     * Kotlin allows you to specify the names of arguments that you’re passing to the function. This makes the function calls more readable.
     * It also allows you to pass the value of a parameter selectively if other parameters have default values.
     */

    /**
     * You can specify the names of arguments while calling the function like this - The below function call will use the default values for parameters a and d.
     */
    arithmeticSeriesSum(n = 10)

    arithmeticSeriesSum(a = 3, n = 10, d = 2)

    /**
     * You can use a mix of named arguments and position-based arguments as long as all the position-based arguments are placed before the named arguments -
     */
    arithmeticSeriesSum(3, n = 10)

    /**
     * The following function call is not allowed since it contains position-based arguments after named arguments -
     */
    // arithmeticSeriesSum(n=10, 2) // error: mixing named and positioned arguments is not allowed


}


@JvmOverloads
fun sum(length: Int = 0, breath: Int = 0, height: Int = 0, depth: Int = 0) {
    println("Length is $length , Breath is $breath and Height is $height and depth is $depth")
}

fun arithmeticSeriesSum(a: Int = 1, n: Int, d: Int = 1) {
    println(n / 2 * (2 * a + (n - 1) * d))
}

@JvmOverloads
fun newFunc(a:Int,b:Int){

}

